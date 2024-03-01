package com.rewards.rewardscalculation.repository;

import com.rewards.rewardscalculation.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    List<CustomerEntity> findAllByIsValid(Character isValid);

    @Query(value = "SELECT customerId FROM CustomerEntity WHERE isValid = 'Y'")
    List<Long> findAllCustomerIds();

    CustomerEntity findByCustomerId(Long customerId);
}
