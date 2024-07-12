package com.javaweb.service.impl;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    /*
     * @Brief : Find all transaction by customer id and transaction type
     * @param id : customer id
     * @param transactionType : transaction type (CSKH, DDX)
     */
    @Override
    public List<TransactionEntity> findTransaction(Long id, String transactionType) {
        return transactionRepository.findByCustomerIdAndCode(id, transactionType);
    }

    /*
     * @Brief: Add or update transaction
     *         --> Because when send data we only send id and new note or information of modified attrs
     *             so we need to get info of the other attrs by id then update new attrs
     */
    @Override
    @Transactional
    public void addOrUpdateTransaction(TransactionDTO transactionDTO) {
        TransactionEntity newTransaction = modelMapper.map(transactionDTO, TransactionEntity.class);
        if(transactionDTO.getId()!=null){ // get previous info of transaction
            TransactionEntity oldTransaction=transactionRepository.findById(transactionDTO.getId()).get();
            newTransaction.setCode(oldTransaction.getCode());
            newTransaction.setCreatedBy(oldTransaction.getCreatedBy());
            newTransaction.setCreatedDate(oldTransaction.getCreatedDate());
        }
        newTransaction.setCustomer(customerRepository.findById(transactionDTO.getCustomerId()).get());
        transactionRepository.save(newTransaction);
    }
}
