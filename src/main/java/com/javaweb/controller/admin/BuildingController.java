package com.javaweb.controller.admin;



import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequest buildingSearchRequest){
        ModelAndView mav = new ModelAndView("admin/building/list");
        Map<Long,String> staffs = userService.getAllStaffs();
        mav.addObject("staffs",staffs);
        mav.addObject("typeCodes",TypeCode.getTypes());
        mav.addObject("districts",DistrictCode.getDistricts());
        List<BuildingSearchResponse> buildingSearchResponses = buildingService.findBuilding(buildingSearchRequest);
        mav.addObject("buildingSearchResponses",buildingSearchResponses);
        return mav;
    }

    @GetMapping("/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute("modelEdit")BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        Map<Long,String> staffs = userService.getAllStaffs();
        mav.addObject("staffs",staffs);
        mav.addObject("typeCodes",TypeCode.getTypes());
        mav.addObject("districts",DistrictCode.getDistricts());
        return mav;
    }

    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView editBuilding(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findById(id);
        mav.addObject("modelEdit",buildingDTO);
        mav.addObject("typeCodes",TypeCode.getTypes());
        mav.addObject("districts",DistrictCode.getDistricts());
        return mav;
    }
}
