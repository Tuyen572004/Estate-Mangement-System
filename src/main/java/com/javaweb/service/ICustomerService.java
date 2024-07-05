package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerEntity> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable);

    List<StaffResponseDTO> loadStaffs(Long id);

    void assignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO);

    void deleteCustomer(List<Long> ids);

    CustomerEntity findById(Long id);

    // findByCodeAndCustomerID
    List<TransactionEntity> findTransaction(Long id, String transactionType);

    void addOrUpdateCustomer(CustomerDTO customerDTO);

    void addOrUpdateTransaction(TransactionDTO transactionDTO);
}
