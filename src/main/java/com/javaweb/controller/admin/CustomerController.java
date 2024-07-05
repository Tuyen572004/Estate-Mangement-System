package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.Status;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;


    @GetMapping(value ="admin/customer-list")
    public ModelAndView listCustomer(@ModelAttribute(SystemConstant.MODEL_SEARCH) CustomerSearchRequest customerSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        // authorize staff can only see their buildings
        if(SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)){
            customerSearchRequest.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        // get staffs, status
        Map<Long,String> staffs = userService.getAllStaffs();
        mav.addObject(SystemConstant.MODEL_STAFFS,staffs);
        mav.addObject(SystemConstant.MODEL_STATUS, Status.type());

        // find all customers and pagination
        DisplayTagUtils.of(request,customerSearchRequest);
        List<CustomerEntity> customers = customerService.findAll(customerSearchRequest,
                PageRequest.of(customerSearchRequest.getPage()-1, customerSearchRequest.getMaxPageItems()));

        // set total items
        customerSearchRequest.setTotalItems(customers.size());
        mav.addObject(SystemConstant.MODEL_CUSTOMER_RESPONSES,customers);

        return mav;
    }

    @GetMapping(value ="admin/customer-edit")
    public ModelAndView addOrUpdateCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject(SystemConstant.MODEL_EDIT,customerDTO);
        mav.addObject(SystemConstant.MODEL_STATUS, Status.type());
        return mav;
    }

    @PreAuthorize("@securityService.hasCustomer(#id)") // authorize staff can only edit their customers
    @GetMapping("/admin/customer-edit-{id}")
    public ModelAndView editCustomer(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerEntity customerEntity = customerService.findById(id);
        mav.addObject(SystemConstant.MODEL_EDIT,customerEntity);
        mav.addObject(SystemConstant.MODEL_STATUS, Status.type());

        // add transaction
        mav.addObject(SystemConstant.MODEL_TRANSACTION, TransactionType.type());

        // get 2 list of 2 type of transaction
        List<TransactionEntity> listTakeCare = customerService.findTransaction(id, "CSKH");
        List<TransactionEntity> listVisit = customerService.findTransaction(id, "DDX");
        mav.addObject("listTakeCare",listTakeCare);
        mav.addObject("listVisit",listVisit);
        return mav;
    }
}
