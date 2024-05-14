package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.data.domain.Pageable;

import java.io.File;
import java.util.*;
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
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UserRepository userRepository;

    private final UploadFileUtils uploadFileUtils;

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
    * + Save rent area entity
    *   --> Find current rent area
    *   --> From new rent area --> Find duplicate rent area --> do nothing
    *   --> Delete unkept rent area
    *   --> Add new rent area
    * */
    @Override
    @Transactional
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) throws MyException {
        try {
            BuildingEntity buildingEntity = buildingConverter.dtoToEntity(buildingDTO);
            saveThumbnail(buildingDTO, buildingEntity);
            buildingRepository.save(buildingEntity);
            // delete all rent area by building id
            rentAreaRepository.deleteByBuildingIdIn(Arrays.asList(buildingEntity.getId()));
            // save all rent area
            rentAreaRepository.saveAll(buildingEntity.getRentAreas());
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
            // delete rent area
            rentAreaRepository.deleteByBuildingIdIn(ids);
            // delete assignment
            assignmentBuildingRepository.deleteByBuildingIdIn(ids);
            // delete building
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
        List<AssignmentBuildingEntity> assignmentBuildingEntities = assignmentBuildingRepository.findByBuildingId(buildingId);
        Set<Long> assginmentStaffIds = assignmentBuildingEntities.stream().map(item -> item.getStaff().getId()).collect(Collectors.toSet());
        // set checked for staffs
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<StaffResponseDTO>();
        for (UserEntity userEntity : userEntities) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(userEntity.getId());
            staffResponseDTO.setFullName(userEntity.getFullName());
            if (assginmentStaffIds.contains(userEntity.getId())) {
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
            // get the current assignments from the database
            List<AssignmentBuildingEntity> currentAssignments = assignmentBuildingRepository.findByBuildingId(assignmentBuildingDTO.getBuildingId());
            // convert new assigned staff ids to set
            Set<Long> newStaffIds = new HashSet<>(assignmentBuildingDTO.getStaffs());
            // convert current ids to set
            Set<Long> currentStaffIds = currentAssignments.stream().map(item -> item.getStaff().getId()).collect(Collectors.toSet());

            // Deleted record (those that are in the database but not in the new assignments)
            Set<Long> deletedStaffIds = new HashSet<>(currentStaffIds);
            deletedStaffIds.removeAll(newStaffIds);
            assignmentBuildingRepository.deleteByBuildingIdAndStaffIdIn(assignmentBuildingDTO.getBuildingId(), deletedStaffIds);

            // Add new assignment (those that are not in the database but in the new assignments)
            newStaffIds.removeAll(currentStaffIds);
            for (Long staffId : newStaffIds) {
                AssignmentBuildingEntity assignment = new AssignmentBuildingEntity();
                assignment.setBuilding(buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get());
                assignment.setStaff(userRepository.findById(staffId).get());
                assignmentBuildingRepository.save(assignment);
            }
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