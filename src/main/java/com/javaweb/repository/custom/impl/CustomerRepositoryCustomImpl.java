package com.javaweb.repository.custom.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    /*
     * @brief: handle join tables
     */
    private void queryJoin(CustomerSearchRequest customerSearchRequest, StringBuilder sql) {
        Long staffId = customerSearchRequest.getStaffId();
        if (staffId != null) {
            sql.append(" JOIN assignmentcustomer ac ON c.id = ac.customerid ");
        }
    }

    private void queryNormalWhere(CustomerSearchRequest customerSearchRequest, StringBuilder where) {
        try {
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String key = item.getName();
                Object value = item.get(customerSearchRequest);
                if(!key.equals("staffId")){
                    if (value != null && !value.toString().equals("")){
                        if(item.getType().getName().equals("java.lang.Long")){
                            where.append(" AND " + key + " = " + value + " ");
                        }
                        else if(item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND " + key + " like N'%" + value + "%' ");
                        }
                    }
                }

            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void querySpecialWhere(CustomerSearchRequest customerSearchRequest, StringBuilder where) {
        Long staffId = customerSearchRequest.getStaffId();
        if (staffId != null) {
            where.append(" and ac.staffId = " + staffId + " ");
        }
    }

    private String buildQuery(CustomerSearchRequest customerSearchRequest){
        StringBuilder query = new StringBuilder(" SELECT * FROM customer c ");
        queryJoin(customerSearchRequest, query);
        query.append(" WHERE c.is_active=1 ");
        queryNormalWhere(customerSearchRequest, query);
        querySpecialWhere(customerSearchRequest, query);
        System.out.println(query);
        return query.toString();
    }

    /*
     * @brief: find all customers by search request
     * Flow of Build Query
     * 1. Init Query
     * 2. If has staffId --> Join table by queryJoin()
     * 3. Append WHERE clause with is_active=1
     * 4. Append WHERE clause with normal fields (not staffId) by queryNormalWhere() - reflection
     * 5. Append WHERE clause with special fields (staffId) by querySpecialWhere()
     */
    @Override
    public List<CustomerEntity> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        String sql = buildQuery(customerSearchRequest);
        Query query = entityManager.createNativeQuery(sql, CustomerEntity.class);
        return query.getResultList();
    }
}
