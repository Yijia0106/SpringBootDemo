package com.rewards.rewardscalculation.controller;

import com.rewards.rewardscalculation.entity.CustomerEntity;
import com.rewards.rewardscalculation.entity.TransactionEntity;
import com.rewards.rewardscalculation.exception.TransactionNotFoundException;
import com.rewards.rewardscalculation.exception.UserNotFoundException;
import com.rewards.rewardscalculation.service.CustomerService;
import com.rewards.rewardscalculation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GeneralController {

    TransactionService transactionService;
    CustomerService customerService;

    @Autowired
    public GeneralController(TransactionService transactionService, CustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    /**
     * get the real-time rewards accumulated by each customer in the past 90 days
     */
    @RequestMapping(value = "/rewards", method = RequestMethod.GET)
    public ResponseEntity<?> getRewardsForAllCustomers() {
        List<Long> customerIds = customerService.findAllCustomerIds();
        Map<Long, String> res = new HashMap<>();

        for (Long customerId : customerIds) {
            String result = transactionService.generateRewardsString(customerId);
            System.out.println(result);
            res.put(customerId, result);
        }
        return new ResponseEntity<Map<Long, String>>(res, HttpStatus.OK);
    }

    /**
     * get all customers
     */
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomers() {
        List<CustomerEntity> customerEntities = customerService.findAllCustomers();
        return new ResponseEntity<List<CustomerEntity>>(customerEntities, HttpStatus.OK);
    }

    /**
     * get a specific customer by id
     */
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getACustomerById(@PathVariable Long id) throws UserNotFoundException{
        CustomerEntity customerEntity = customerService.findACustomerById(id);
        if(customerEntity == null){
            throw new UserNotFoundException("No qualified user exists.");
        }
        return new ResponseEntity<CustomerEntity>(customerEntity, HttpStatus.OK);
    }

    /**
     * create a new customer entry
     */
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<?> createACustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.saveACustomer(customerEntity);
        return new ResponseEntity<>("New customer created!", HttpStatus.CREATED);
    }

    /**
     * delete (deactivate) a customer
     */
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deactivateACustomer(@PathVariable Long id){
        CustomerEntity customerEntity = customerService.findACustomerById(id);
        if(customerEntity == null){
            throw new UserNotFoundException("No qualified user exists.");
        }

        customerEntity.setIsValid('N');
        customerService.saveACustomer(customerEntity);
        return new ResponseEntity<>("Successfully deactivate the user!", HttpStatus.OK);
    }

    /**
     * get all transactions
     */
    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public ResponseEntity<?> getTransactions() {
        List<TransactionEntity> transactionEntities = transactionService.findAllTransactions();
        return new ResponseEntity<List<TransactionEntity>>(transactionEntities, HttpStatus.OK);
    }

    /**
     * create a new transaction entry
     */
    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<?> createATransaction(@RequestBody TransactionEntity transactionEntity) {
        transactionService.saveATransaction(transactionEntity);
        return new ResponseEntity<>("New transaction created!", HttpStatus.CREATED);
    }

    /**
     * delete a transaction entry
     */
    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteATransaction(@PathVariable Long id){
        transactionService.deleteATransaction(id);
        return new ResponseEntity<>("The transaction is deleted.", HttpStatus.OK);
    }

    /**
     * update the amount for a transaction entry
     */
    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> deleteATransaction(@PathVariable Long id, @RequestBody double amt){
        TransactionEntity transactionEntity = transactionService.findATransactionById(id);
        if(transactionEntity == null){
            throw new TransactionNotFoundException("No qualified transaction exists.");
        }
        transactionEntity.setAmount(amt);
        transactionService.saveATransaction(transactionEntity);
        return new ResponseEntity<>("The new transaction amount is saved.", HttpStatus.OK);
    }


}
