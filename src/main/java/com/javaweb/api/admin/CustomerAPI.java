package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value="customerAPIOfAdmin")
@RequestMapping("/api/customers")
public class CustomerAPI {
    @Autowired
    ICustomerService customerService;
    @Autowired
    private SecurityService securityService;

    @PostMapping
    public String addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            customerService.addOrUpdateCustomer(customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "Add customer fail";
        }
        if(customerDTO.getId() != null) {
            return "Update customer success";
        }
        return "Create customer success";
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id) {
        // load all staffs
        List<StaffResponseDTO> staffResponseDTOS = customerService.loadStaffs(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOS);
        return responseDTO;
    }

    @PutMapping()
    public String updateAssignmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        if(!securityService.hasCustomer(assignmentCustomerDTO.getCustomerId())){ // authorize
            throw new AccessDeniedException("Access denied");
        }
        try {
            customerService.assignmentCustomer(assignmentCustomerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail to assign customer";
        }
        return "Assign customer success";
    }

    @DeleteMapping()
    public String deleteCustomer(@RequestBody List<Long> ids){
        try {
            customerService.deleteCustomer(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return "Delete customer fail";
        }
        return "Delete customer success";
    }

}
