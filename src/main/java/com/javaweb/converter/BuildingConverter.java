package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.utils.MapUtils;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.javaweb.utils.StringUtils.*;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO entityToDto(BuildingEntity item) {
        BuildingDTO buildingDTO = modelMapper.map(item, BuildingDTO.class);

        //typecode
        String typeCode = item.getType();
        // seperate comma
        List<String> typeCodes = Arrays.asList(typeCode.split(","));
        //typeCodes = typeCodes.stream().map(it -> TypeCode.valueOf(it).getTypeCode()).collect(Collectors.toList());
        buildingDTO.setTypeCode(typeCodes);

        // list rent area -> 100,200
        String rentArea = item.getRentAreas().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
        buildingDTO.setRentArea(rentArea);

        return buildingDTO;
    }

    //    public BuildingSearchResponse DtoToSearchResponse(BuildingDTO buildingDTO) {
//        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingDTO, BuildingSearchResponse.class);
//        String address = buildingDTO.getStreet() + ", " + buildingDTO.getWard() + ", " + buildingDTO.getDistrict();
//        buildingSearchResponse.setAddress(address);
//        return buildingSearchResponse;
//    }
    public BuildingSearchResponse entityToSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        // get value of enum district
        String district = DistrictCode.valueOf(buildingEntity.getDistrict()).getDistrictName();
        // address = street + ward + district
        List<String> addr= new ArrayList<>();
        if(check(buildingEntity.getStreet()))
            addr.add(buildingEntity.getStreet());
        if(check(buildingEntity.getWard()))
            addr.add(buildingEntity.getWard());
        if(check(district))
            addr.add(district);
        String address = addr.stream().collect(Collectors.joining(", "));
        buildingSearchResponse.setAddress(address);
        // rent area
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
        String rentArea = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
        buildingSearchResponse.setRentArea(rentArea);
        return buildingSearchResponse;
    }

    public BuildingSearchBuilder searchRequestToSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(buildingSearchRequest.getName(), String.class))
                .setFloorArea(MapUtils.getObject(buildingSearchRequest.getFloorArea(), Long.class))
                .setWard(MapUtils.getObject(buildingSearchRequest.getWard(), String.class))
                .setStreet(MapUtils.getObject(buildingSearchRequest.getStreet(), String.class))
                .setDirection(MapUtils.getObject(buildingSearchRequest.getDirection(), String.class))
                .setLevel(MapUtils.getObject(buildingSearchRequest.getLevel(), Long.class))
                .setDistrict(MapUtils.getObject(buildingSearchRequest.getDistrict(), String.class))
                .setNumberOfBasement(MapUtils.getObject(buildingSearchRequest.getNumberOfBasement(), Long.class))
                .setTypeCode(buildingSearchRequest.getTypeCode())
                .setManagerName(MapUtils.getObject(buildingSearchRequest.getManagerName(), String.class))
                .setManagerPhone(MapUtils.getObject(buildingSearchRequest.getManagerPhone(), String.class))
                .setRentPriceFrom(MapUtils.getObject(buildingSearchRequest.getRentPriceFrom(), Long.class))
                .setRentPriceTo(MapUtils.getObject(buildingSearchRequest.getRentPriceTo(), Long.class))
                .setAreaFrom(MapUtils.getObject(buildingSearchRequest.getAreaFrom(), Long.class))
                .setAreaTo(MapUtils.getObject(buildingSearchRequest.getAreaTo(), Long.class))
                .setStaffId(MapUtils.getObject(buildingSearchRequest.getStaffId(), Long.class))
                .build();
        return buildingSearchBuilder;
    }

    public BuildingEntity dtoToEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        // district --> OK
        // typeCode --> DTO is List<String> - Entity is String --> need to convert
        String type = buildingDTO.getTypeCode().stream().collect(Collectors.joining(","));
        buildingEntity.setType(type);
        // rentArea
        if (check(buildingDTO.getRentArea())) {
            List<Long> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(","))
                    .map(it -> Long.parseLong(it.trim()))
                    .collect(Collectors.toList());
            List<RentAreaEntity> rentAreaEntities = new ArrayList<RentAreaEntity>();
            for (Long item : rentAreas) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(item);
                rentAreaEntity.setBuilding(buildingEntity);
                rentAreaEntities.add(rentAreaEntity);
            }
            buildingEntity.setRentAreas(rentAreaEntities);
        }
        return buildingEntity;
    }
}