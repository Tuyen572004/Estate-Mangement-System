package com.javaweb.service;


import java.util.List;
import java.util.Map;

import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.data.domain.Pageable;


public interface IBuildingService {
    List<BuildingSearchResponse> findBuilding(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    void addOrUpdateBuilding(BuildingDTO buildingDTO) throws MyException;

    BuildingDTO findById(Long id);
    List<StaffResponseDTO> loadStaffs(Long buildingId);
    void deleteBuilding(List<Long> ids) throws MyException;
    void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) throws MyException;
}