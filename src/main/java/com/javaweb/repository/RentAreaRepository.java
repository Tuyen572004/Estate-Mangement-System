package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity,Long> {
    List<RentAreaEntity> findByBuildingId(Long buildingId);
    void deleteByBuildingIdAndValueIn(Long buildingId, List<Long> deletedRentAreaValues);
    void deleteByBuildingIdIn(List<Long> buildingId);
}
