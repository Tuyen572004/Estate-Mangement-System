package com.javaweb.builder;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BuildingSearchBuilder {
    private String name;
    private Long floorArea;
    private String ward;
    private String street;
    private String direction;
    private Long level;
    private String district;
    private Long numberOfBasement;
    private List<String> typeCode = new ArrayList<String>();
    private String managerName;
    private String managerPhone;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private Long areaFrom;
    private Long areaTo;
    private Long staffId;

    public BuildingSearchBuilder(Builder builder) {
        name=builder.name;
        floorArea=builder.floorArea;
        ward=builder.ward;
        street=builder.street;
        direction=builder.direction;
        level=builder.level;
        district=builder.district;
        numberOfBasement=builder.numberOfBasement;
        typeCode = builder.typeCode;
        managerName=builder.managerName;
        managerPhone=builder.managerPhone;
        rentPriceFrom=builder.rentPriceFrom;
        rentPriceTo=builder.rentPriceTo;
        areaFrom=builder.areaFrom;
        areaTo=builder.areaTo;
        staffId=builder.staffId;
    }

    public static class Builder{
        private String name;
        private Long floorArea;
        private String ward;
        private String street;
        private String direction;
        private Long level;
        private String district;
        private Long numberOfBasement;
        private List<String> typeCode = new ArrayList<String>();
        private String managerName;
        private String managerPhone;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private Long areaFrom;
        private Long areaTo;
        private Long staffId;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }
        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }
        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }
        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }
        public Builder setLevel(Long level) {
            this.level = level;
            return this;
        }
        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }
        public Builder setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }
        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }
        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }
        public Builder setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }
        public Builder setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }
        public Builder setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }
        public Builder setAreaFrom(Long areaFrom) {
            this.areaFrom = areaFrom;
            return this;
        }
        public Builder setAreaTo(Long areaTo) {
            this.areaTo = areaTo;
            return this;
        }
        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}

