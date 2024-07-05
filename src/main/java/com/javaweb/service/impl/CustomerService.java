package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<CustomerEntity> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        return customerRepository.findAll(customerSearchRequest, pageable);
    }

    /*
     * @Brief: Find staffs manage customer by customer id then return to view in modal fade
     *        to see all staffs and check staffs manage customer
     *         --> Find all staffs and set checked for them if they manage customer else set empty
     * */
    @Override
    public List<StaffResponseDTO> loadStaffs(Long customerId) {
        // find all staffs
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        // find staff manage building has id
        List<AssignmentCustomerEntity> assignmentCustomerEntities = assignmentCustomerRepository.findByCustomerId(customerId);
        Set<Long> assginmentStaffIds = assignmentCustomerEntities.stream().map(item -> item.getStaff().getId()).collect(Collectors.toSet());
        // set checked for staffs
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<StaffResponseDTO>();
        for (UserEntity userEntity : userEntities) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(userEntity.getId());
            staffResponseDTO.setFullName(userEntity.getFullName());
            if (assginmentStaffIds.contains(userEntity.getId())) {
                staffResponseDTO.setChecked("checked");
            } else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        return staffResponseDTOS;
    }

    /*
     * @Brief: Assign customer to staffs
     *         --> Delete all assignment customer before by customer id
     *         --> Save new assignment
     */
    @Override
    @Transactional
    public void assignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        // delete all assignment customer before by customer id
        assignmentCustomerRepository.deleteByCustomerId(assignmentCustomerDTO.getCustomerId());
        // save new assignment
        CustomerEntity customerEntity = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        for(Long staffId: assignmentCustomerDTO.getStaffs()){
            AssignmentCustomerEntity assignmentCustomerEntity = new AssignmentCustomerEntity();
            assignmentCustomerEntity.setCustomer(customerEntity);
            assignmentCustomerEntity.setStaff(userRepository.findById(staffId).get());
            assignmentCustomerRepository.save(assignmentCustomerEntity);
        }
    }

    /*
     * @Brief: Delete customer by id - set isActive = 0
     * @param ids : list of customer id
     */
    @Override
    @Transactional
    public void deleteCustomer(List<Long> ids) {
        for (Long id : ids) {
            CustomerEntity customerEntity = customerRepository.findById(id).get();
            customerEntity.setIsActive(Long.valueOf(0));
            customerRepository.save(customerEntity);
        }
    }

    @Override
    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id).get();
    }

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
     * @Brief: Add or update customer --> If add a new customer then set isActive = 1
     */
    @Override
    @Transactional
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        if(customerDTO.getId()!=null){
            CustomerEntity oldCustomer = customerRepository.findById(customerDTO.getId()).get();
            customerEntity.setCreatedBy(oldCustomer.getCreatedBy());
            customerEntity.setCreatedDate(oldCustomer.getCreatedDate());
        }
        customerEntity.setIsActive(Long.valueOf(1)); // in case add new customer
        customerRepository.save(customerEntity);
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
