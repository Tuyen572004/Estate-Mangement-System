// WITH CASCADE TEMPORARY WE DON'T NEED THIS REPOSITORY


//package com.javaweb.repository;
//
//import com.javaweb.entity.AssignmentBuildingEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Set;
//
//public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
//    List<AssignmentBuildingEntity> findByBuildingId(Long buildingId);
//
//    void deleteByBuildingIdIn(List<Long> ids);
//
//    void deleteByBuildingIdAndStaffIdIn(Long buildingId, Set<Long> deletedStaffIds);
//}
