package com.javaweb.service;

import com.javaweb.constant.SystemConstant;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component("securityService")
public class SecurityService {

    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    private boolean checkManager() {
        return SecurityUtils.getAuthorities().contains(SystemConstant.MANAGER_ROLE);
    }

    private boolean checkStaff() {
        return SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE);
    }

    public boolean hasCustomer(Long id) {
        // if manager then return true
        if (checkManager()) {
            return true;
        }

        // Check if the user has the necessary role
        if (checkStaff()) {
            return assignmentCustomerRepository.findByStaffId(SecurityUtils.getPrincipal().getId()).stream()
                    .map(it -> it.getCustomer().getId())
                    .collect(Collectors.toList())
                    .contains(id);
        }
        return false;
    }

    public boolean hasBuilding(Long id) {
        // if manager then return true
        if (checkManager()) {
            return true;
        }
        if(checkStaff()){
            return assignmentBuildingRepository.findByStaffId(SecurityUtils.getPrincipal().getId()).stream()
                    .map(it -> it.getBuilding().getId())
                    .collect(Collectors.toList())
                    .contains(id);
        }

        return false;
    }


}