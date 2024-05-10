package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="building")
public class BuildingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="street")
    private String street;

    @Column(name="ward")
    private String ward;

    @Column(name="district")
    private String district;

    @Column(name="structure")
    private String structure;

    @Column(name="numberofbasement")
    private Long numberOfBasement;

    @Column(name="floorarea")
    private Long floorArea;

    @Column(name="direction")
    private String direction;

    @Column(name="level")
    private String level;

    @Column(name="rentprice")
    private Long rentPrice;

    @Column(name="rentpricedescription")
    private String rentDescription;

    @Column(name="servicefee")
    private Long serviceFee;

    @Column(name="carfee")
    private Long carFee;

    @Column(name="motofee")
    private Long motorbikeFee;

    @Column(name="overtimefee")
    private String overtimeFee;

    @Column(name="waterfee")
    private String waterFee;

    @Column(name="electricityfee")
    private String electricityFee;

    @Column(name="deposit")
    private String deposit;

    @Column(name="payment")
    private String payment;

    @Column(name="renttime")
    private String rentTime;

    @Column(name="decorationtime")
    private String decorationTime;

    @Column(name="brokeragefee")
    private Long brokerageFee;

    @Column(name="type")
    private String type;

    @Column(name="note")
    private String note;

    @Column(name="linkofbuilding")
    private String link;

    @Column(name="map")
    private String map;

    @Column(name="managername")
    private String managerName;

    @Column(name="managerphone")
    private String managerPhone;

    @OneToMany(mappedBy="building",fetch=FetchType.LAZY)
    private List<RentAreaEntity> rentAreas = new ArrayList<>();

//    @OneToMany(mappedBy="building",fetch=FetchType.LAZY)
//    private List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();

}