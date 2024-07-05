package com.javaweb.repository;

import com.javaweb.entity.AssignmentCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.charset.Charset;
import java.util.List;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomerEntity, Long> {
    List<AssignmentCustomerEntity> findByCustomerId(Long customerId);

    void deleteByCustomerId(Long customerId);

    List<AssignmentCustomerEntity> findByStaffId(Long id);
}
