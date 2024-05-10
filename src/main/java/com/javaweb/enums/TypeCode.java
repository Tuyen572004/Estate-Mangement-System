package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum TypeCode {
    NOI_THAT("Nội thất"),
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn");
    private final String typeCode;
    TypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getTypeCode(){
        return typeCode;
    }
    public static Map<String,String> getTypes(){
        Map<String,String> types = new HashMap<String,String>();
        for(TypeCode typeCode : TypeCode.values()){
            types.put(typeCode.name(),typeCode.typeCode);
        }
        return types;
    }
}
