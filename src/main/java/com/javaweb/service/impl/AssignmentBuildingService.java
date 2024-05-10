import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;//package com.javaweb.service.impl;
//
//import com.javaweb.entity.AssignmentBuildingEntity;
//import com.javaweb.entity.UserEntity;
//import com.javaweb.model.dto.AssignmentBuildingDTO;
//import com.javaweb.model.response.StaffResponseDTO;
//import com.javaweb.repository.AssignmentBuildingRepository;
//import com.javaweb.repository.BuildingRepository;
//import com.javaweb.repository.UserRepository;
//import com.javaweb.service.IAssignmentBuildingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class AssignmentBuildingService implements IAssignmentBuildingService {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private AssignmentBuildingRepository assignmentBuildingRepository;
//    @Autowired
//    private BuildingRepository buildingRepository;
//
//    @Override
//    public List<StaffResponseDTO> loadStaffs(Long buildingId) {
//     find all staffs
//    List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
//    // find staff manage building has id
//    List<AssignmentBuildingEntity> assignmentBuildingEntities = assignmentBuildingRepository.findByBuildingId(buildingId);
//    List<Long>assginmentStaffIds = assignmentBuildingEntities.stream().map(item -> item.getStaff().getId()).collect(Collectors.toList());
//    // set checked for staffs
//    List<StaffResponseDTO> staffResponseDTOS = new ArrayList<StaffResponseDTO>();
//            for (UserEntity userEntity : userEntities) {
//    StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
//                staffResponseDTO.setStaffId(userEntity.getId());
//            staffResponseDTO.setFullName(userEntity.getFullName());
//            if (assginmentStaffIds.contains(userEntity.getId())) {
//            staffResponseDTO.setChecked("checked");
//                } else {
//                        staffResponseDTO.setChecked("");
//                }
//                        staffResponseDTOS.add(staffResponseDTO);
//            }
//                    return staffResponseDTOS;
//    }
//
//    @Override
//    @Transactional
//    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
//        // delete all staffs by building id
//        assignmentBuildingRepository.deleteByBuildingIdIn(Arrays.asList(assignmentBuildingDTO.getBuildingId()));
//        // save all staffs by building id
//        for(Long staffId : assignmentBuildingDTO.getStaffs()) {
//            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
//            assignmentBuildingEntity.setBuilding(buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get());
//            assignmentBuildingEntity.setStaff(userRepository.findById(staffId).get());
//            assignmentBuildingRepository.save(assignmentBuildingEntity);
//        }
//
//    }
//}
