package com.rewards.rewardscalculation.repository;

import com.rewards.rewardscalculation.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByDateBetween(Date from, Date to);

    List<TransactionEntity> findAllByDateBetweenAndCustomerIdOrderByDate(Date from, Date to, Long customerId);

    List<TransactionEntity> findAllByCustomerId(Long customerId);

    List<TransactionEntity> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TransactionEntity WHERE tId = ?1")
    void deleteATransaction(Long tId);

    TransactionEntity findATransactionByTId(long tId);

}
