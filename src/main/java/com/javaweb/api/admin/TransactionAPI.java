package com.javaweb.api.admin;


import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javaweb.service.SecurityService;

@RestController(value="transactionAPIOfAdmin")
@RequestMapping("/api/customers/transaction")
public class TransactionAPI {
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private SecurityService securityService;

    @PostMapping
    public String addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
        // authorize
        if(!securityService.hasCustomer(transactionDTO.getCustomerId())){ // authorize
            throw new AccessDeniedException("Access denied");
        }
        // do something
        try {
            transactionService.addOrUpdateTransaction(transactionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail to add or update transaction";
        }
        if(transactionDTO.getId() != null) {
            return "Update transaction success";
        }
        return "Add transaction success";
    }
}
