package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    // findByCodeAndCustomerID
    List<TransactionEntity> findTransaction(Long id, String transactionType);
    void addOrUpdateTransaction(TransactionDTO transactionDTO);
}
