package com.rewards.rewardscalculation.service;

import com.rewards.rewardscalculation.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {

    List<Long> findAllCustomerIds();

    List<CustomerEntity> findAllCustomers();

    void saveACustomer(CustomerEntity customerEntity);

    void deactivateACustomer(Long customerId);

    CustomerEntity findACustomerById(Long id);
}
