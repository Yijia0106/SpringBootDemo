package com.rewards.rewardscalculation.service;

import com.rewards.rewardscalculation.entity.TransactionEntity;
import com.rewards.rewardscalculation.entity.CustomerEntity;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    List<TransactionEntity> findBetweenDateAndCustomerId(Date from, Date to, Long customerId);

    List<TransactionEntity> findBetweenDate(Date from, Date to);

    List<TransactionEntity> findByCustomerId(Long customerId);

    String generateRewardsString(Long customerId);

    List<TransactionEntity> findAllTransactions();

    void saveATransaction(TransactionEntity transactionEntity);

    void deleteATransaction(Long tId);

//    TransactionEntity findATransactionById(Long tId);
}
