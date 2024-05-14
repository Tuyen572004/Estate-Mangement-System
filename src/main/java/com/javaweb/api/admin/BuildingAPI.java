package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.IAssignmentBuildingService;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.impl.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;

//    @Autowired
//    private IAssignmentBuildingService assignmentBuildingService;

//    public BuildingAPI(BuildingService buildingService) {
//        this.buildingService = buildingService;
//    }

    @PostMapping
    public String addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        try {
            buildingService.addOrUpdateBuilding(buildingDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "Create building fail";
        }
        return "Create building success";
    }

    @DeleteMapping
    public String deleteBuilding(@RequestBody List<Long> ids) {
        try {
            buildingService.deleteBuilding(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return "Delete building fail";
        }
        return "Delete building success";
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id) {
        // load all staffs
        List<StaffResponseDTO> staffResponseDTOS = buildingService.loadStaffs(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOS);
        return responseDTO;
    }

    @PutMapping()
    public String updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        try {
//            assignmentBuildingService.updateAssignmentBuilding(assignmentBuildingDTO);
            buildingService.assignmentBuilding(assignmentBuildingDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "Không thực hiện giao tòa nhà thành công";
        }
        return "Giao tòa nhà thành công";
    }
}
