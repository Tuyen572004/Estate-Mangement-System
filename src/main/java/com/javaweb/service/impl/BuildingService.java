package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BuildingService implements IBuildingService {

    @Autowired(required = true)
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private UserRepository userRepository;

    private final UploadFileUtils uploadFileUtils;
    @Autowired
    private ModelMapper modelMapper;

    public BuildingService(UploadFileUtils uploadFileUtils) {
        this.uploadFileUtils = uploadFileUtils;
    }


    /*
    @Brief: Find all buildings by search request then convert to BuildingSearchResponse and return to view
    * */
    @Override
    public List<BuildingSearchResponse> findBuilding(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        List<BuildingSearchResponse> result = new ArrayList<BuildingSearchResponse>();
        BuildingSearchBuilder buildingSearchBuilder = buildingConverter.searchRequestToSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder, pageable);
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingConverter.entityToSearchResponse(item);
            result.add(buildingSearchResponse);
        }
        return result;
    }

    /*
    * @Brief : Add or update building
    * + Send buildingDTO to service then convert to entity --> Save building entity
    * + Save rent area entity --> Reset list rent area --> Save
    *                         --> Thanks to orphanRemoval = true --> Delete all rent area then save new rent area --> INEFFICIENT
    * */
    @Override
    @Transactional
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) throws MyException {
        try {
            BuildingEntity buildingEntity = buildingConverter.dtoToEntity(buildingDTO);

            if (buildingDTO.getId() != null) { // update
                BuildingEntity foundBuilding = buildingRepository.findById(buildingDTO.getId())
                        .orElseThrow(() -> new MyException("Không tìm thấy building"));
                buildingEntity.setImage(foundBuilding.getImage());
                buildingEntity.setStaffs(foundBuilding.getStaffs());
            }
            saveThumbnail(buildingDTO, buildingEntity);
            buildingRepository.save(buildingEntity);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("Error when save building");
        }
    }

    /*
     * @Brief: Update building --> Send id then find building by id and return to view
     *                             to see already information of building
     * */
    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingConverter.entityToDto(buildingEntity);
        return buildingDTO;
    }

    @Override
    @Transactional
    public void deleteBuilding(List<Long> ids) throws MyException {
        try {
            buildingRepository.deleteByIdIn(ids);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("Error when delete building");
        }
    }

    /*
     * @Brief: Find staffs manage building by building id then return to view in modal fade
     *        to see all staffs and check staffs manage building
     * */
    @Override
    public List<StaffResponseDTO> loadStaffs(Long buildingId) {
        // find all staffs
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        // find staff manage building has id
        List<UserEntity> staffs = buildingRepository.findById(buildingId).get().getStaffs();
        Set<Long> staffIds = staffs.stream().map(item -> item.getId()).collect(Collectors.toSet());
        // set checked for staffs
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<StaffResponseDTO>();
        for (UserEntity userEntity : userEntities) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(userEntity.getId());
            staffResponseDTO.setFullName(userEntity.getFullName());
            if (staffIds.contains(userEntity.getId())) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        return staffResponseDTOS;
    }


    @Override
    @Transactional
    public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) throws MyException {
        try {
            BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
            List<UserEntity> staffs = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
            buildingEntity.setStaffs(staffs);
            buildingRepository.save(buildingEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("Error when update assignment building");
        }
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());

            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }


}