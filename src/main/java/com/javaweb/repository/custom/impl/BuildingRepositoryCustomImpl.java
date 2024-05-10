package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import com.javaweb.entity.BuildingEntity;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    /*
     * @brief: handle join tables
     */
    private void queryJoin(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        if (rentAreaFrom != null || rentAreaTo != null) {
            sql.append(" JOIN rentarea ra ON b.id = ra.buildingid ");
        }
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            sql.append(" JOIN assignmentbuilding ab ON b.id = ab.buildingid");
        }
    }
    /*
     * @brief: handle normal where query normal : use like/= and use and to assemble
     * condition
     */

    private void queryNormalWhere(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields(); // reflection
            for (Field item : fields) {
                item.setAccessible(true);
                String key = item.getName();
                Object value = item.get(buildingSearchBuilder);
                if (!(key.equals("staffId") || key.equals("typeCode") || key.startsWith("area")
                        || key.startsWith("rentPrice"))) {
                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long")) {
                            where.append(" and b." + key + " = " + value + " ");
                        } else {
                            where.append(" and b." + key + " like N'%" + value + "%' ");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * @brief: handle special where query special : field need to use >=, <=, join
     * --> find in other tables
     */

    private void querySpecialWhere(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        // handle staffId
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" and ab.staffId = " + staffId + " ");
        }
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        // handle rentArea
        if (rentAreaFrom != null) {
            where.append(" and ra.value >= " + rentAreaFrom);
        }
        if (rentAreaTo != null) {
            where.append(" and ra.value <=" + rentAreaTo);
        }
        // handle rentPrice
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (rentPriceFrom != null) {
            where.append(" and b.rentprice >= " + rentPriceFrom);
        }
        if (rentPriceTo != null) {
            where.append(" and b.rentprice <=" + rentPriceTo);
        }
        // handle typecode tang-tret
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() > 0) {
            where.append(" and ( ");
            // each value turn into N'%tang-tret%' and joining by ','
            String type = typeCode.stream().map(value -> " b.type like N'%" + value + "%' ")
                    .collect(Collectors.joining(" or "));
            where.append(type);
            where.append(") ");
        }
    }

    private StringBuilder buildQuery(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM Building b ");
        queryJoin(buildingSearchBuilder, sql);
        sql.append(" where 1=1 ");
        queryNormalWhere(buildingSearchBuilder, sql);
        querySpecialWhere(buildingSearchBuilder, sql);
        System.out.println(sql); // For debug
        return sql;
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = buildQuery(buildingSearchBuilder);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();

    }

}