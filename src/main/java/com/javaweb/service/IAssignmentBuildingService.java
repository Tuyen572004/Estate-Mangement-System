package com.javaweb.service;

import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.List;

public interface IAssignmentBuildingService {
    List<StaffResponseDTO> loadStaffs(Long buildingId);
    void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) throws MyException;
}
