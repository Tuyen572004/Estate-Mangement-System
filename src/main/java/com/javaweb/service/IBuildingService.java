package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;

public interface IBuildingService {
    List<BuildingSearchResponse> findBuilding(BuildingSearchRequest buildingSearchRequest);
    void addOrUpdateBuilding(BuildingDTO buildingDTO);

    BuildingDTO findById(Long id);
    List<StaffResponseDTO> loadStaffs(Long buildingId);
    void deleteBuilding(List<Long> ids) throws MyException;
    void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) throws MyException;
}