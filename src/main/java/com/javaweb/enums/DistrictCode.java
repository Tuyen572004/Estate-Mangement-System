package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum DistrictCode {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4");

    private final String districtName;
    DistrictCode(String districtName) {
        this.districtName = districtName;
    }
    public String getDistrictName(){
        return districtName;
    }
    public static Map<String,String> getDistricts(){
        Map<String,String> districts = new HashMap<String,String>();
        for(DistrictCode districtCode : DistrictCode.values()){
            districts.put(districtCode.name(),districtCode.districtName);
        }
        return districts;
    }

}
