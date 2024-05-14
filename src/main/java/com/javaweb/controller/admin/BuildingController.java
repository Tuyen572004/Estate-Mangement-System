package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.BuildingService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute(SystemConstant.MODEL_SEARCH) BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        // get staffs, building types, districts send to view
        Map<Long,String> staffs = userService.getAllStaffs();
        mav.addObject(SystemConstant.MODEL_STAFFS,staffs);
        mav.addObject(SystemConstant.MODEL_BUILDING_TYPES,TypeCode.getTypes());
        mav.addObject(SystemConstant.MODEL_DISTRICTS,DistrictCode.getDistricts());
        // Pagination
        DisplayTagUtils.of(request,buildingSearchRequest);
        List<BuildingSearchResponse> buildingSearchResponses = buildingService.findBuilding(buildingSearchRequest,
                                        PageRequest.of(buildingSearchRequest.getPage()-1,buildingSearchRequest.getMaxPageItems()));
        // set total items
        buildingSearchRequest.setTotalItems(buildingSearchResponses.size());
        mav.addObject(SystemConstant.MODEL_BUILDING_SEARCH_RESPONSES,buildingSearchResponses);
        return mav;
    }

    @GetMapping("/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute(SystemConstant.MODEL_EDIT)BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        Map<Long,String> staffs = userService.getAllStaffs();
        mav.addObject(SystemConstant.MODEL_STAFFS,staffs);
        mav.addObject(SystemConstant.MODEL_BUILDING_TYPES,TypeCode.getTypes());
        mav.addObject(SystemConstant.MODEL_DISTRICTS,DistrictCode.getDistricts());
        return mav;
    }

    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView editBuilding(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findById(id);
        mav.addObject(SystemConstant.MODEL_EDIT,buildingDTO);
        mav.addObject(SystemConstant.MODEL_BUILDING_TYPES,TypeCode.getTypes());
        mav.addObject(SystemConstant.MODEL_DISTRICTS,DistrictCode.getDistricts());
        return mav;
    }
}
