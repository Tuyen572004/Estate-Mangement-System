// WITH CASCADE TEMPORARY WE DON'T NEED THIS ENTITY


//package com.javaweb.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Setter
//@Getter
//@Entity(name = "assignmentbuilding")
//public class AssignmentBuildingEntity extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "staffid")
//    private UserEntity staff;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "buildingid")
//    private BuildingEntity building;
//}
