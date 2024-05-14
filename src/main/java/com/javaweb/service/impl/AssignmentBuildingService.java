import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
//        @Override
//        @Transactional
//        public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) throws MyException {
//            try {
//                // get the current assignments from the database
//                List<AssignmentBuildingEntity> currentAssignments = assignmentBuildingRepository.findByBuildingId(assignmentBuildingDTO.getBuildingId());
//                // convert new assigned staff ids to set
//                Set<Long> newStaffIds = new HashSet<>(assignmentBuildingDTO.getStaffs());
//                // convert current ids to set
//                Set<Long> currentStaffIds = currentAssignments.stream().map(item -> item.getStaff().getId()).collect(Collectors.toSet());
//
//                // Deleted record (those that are in the database but not in the new assignments)
//                Set<Long> deletedStaffIds = new HashSet<>(currentStaffIds);
//                deletedStaffIds.removeAll(newStaffIds);
//                assignmentBuildingRepository.deleteByBuildingIdAndStaffIdIn(assignmentBuildingDTO.getBuildingId(), deletedStaffIds);
//
//                // Add new assignment (those that are not in the database but in the new assignments)
//                newStaffIds.removeAll(currentStaffIds);
//                for (Long staffId : newStaffIds) {
//                    AssignmentBuildingEntity assignment = new AssignmentBuildingEntity();
//                    assignment.setBuilding(buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get());
//                    assignment.setStaff(userRepository.findById(staffId).get());
//                    assignmentBuildingRepository.save(assignment);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new MyException("Error when update assignment building");
//            }
//        }
