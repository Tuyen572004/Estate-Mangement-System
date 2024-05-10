package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BuildingService implements IBuildingService {

    @Autowired(required=true)
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UserRepository userRepository;

    /*
    @Brief: Find all buildings by search request then convert to BuildingSearchResponse and return to view
    * */
    @Override
    public List<BuildingSearchResponse> findBuilding(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingSearchResponse> result = new ArrayList<BuildingSearchResponse>();
        BuildingSearchBuilder buildingSearchBuilder=buildingConverter.searchRequestToSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        for(BuildingEntity item : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse=buildingConverter.entityToSearchResponse(item);
            result.add(buildingSearchResponse);
        }
        return result;
    }

    @Override
    @Transactional
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.dtoToEntity(buildingDTO);
        buildingRepository.save(buildingEntity);
        // delete all rent area by building id
        rentAreaRepository.deleteByBuildingIdIn(Arrays.asList(buildingEntity.getId()));
        // save all rent area
        rentAreaRepository.saveAll(buildingEntity.getRentAreas());
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
            if (assginmentStaffIds.contains(userEntity.getId())){
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
        try{
            // delete all staffs by building id
            assignmentBuildingRepository.deleteByBuildingIdIn(Arrays.asList(assignmentBuildingDTO.getBuildingId()));
            // save all staffs by building id
            for(Long staffId : assignmentBuildingDTO.getStaffs()) {
                AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
                assignmentBuildingEntity.setBuilding(buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get());
                assignmentBuildingEntity.setStaff(userRepository.findById(staffId).get());
                assignmentBuildingRepository.save(assignmentBuildingEntity);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new MyException("Error when update assignment building");
        }
    }

}