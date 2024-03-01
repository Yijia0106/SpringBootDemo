package com.rewards.rewardscalculation.service;

import com.rewards.rewardscalculation.entity.CustomerEntity;
import com.rewards.rewardscalculation.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Long> findAllCustomerIds() {
        return customerRepository.findAllCustomerIds();
    }

    @Override
    public List<CustomerEntity> findAllCustomers() {
        return customerRepository.findAllByIsValid('Y');
    }

    @Override
    public void saveACustomer(CustomerEntity customerEntity) {
        customerRepository.saveAndFlush(customerEntity);
    }

    @Override
    public CustomerEntity findACustomerById(Long id) {
        return customerRepository.findByCustomerId(id);
    }
}
