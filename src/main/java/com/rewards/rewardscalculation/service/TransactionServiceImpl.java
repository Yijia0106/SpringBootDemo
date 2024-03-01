package com.rewards.rewardscalculation.service;

import com.rewards.rewardscalculation.entity.TransactionEntity;
import com.rewards.rewardscalculation.entity.CustomerEntity;
import com.rewards.rewardscalculation.repository.TransactionRepository;
import com.rewards.rewardscalculation.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionEntity> findBetweenDateAndCustomerId(Date from, Date to, Long customerId) {
        return transactionRepository.findAllByDateBetweenAndCustomerId(from, to, customerId);
    }

    @Override
    public List<TransactionEntity> findBetweenDate(Date from, Date to) {
        return null;
    }

    @Override
    public List<TransactionEntity> findByCustomerId(Long customerId) {
        return transactionRepository.findAllByCustomerId(customerId);
    }


    @Override
    public String generateRewardsString(Long customerId) {
        StringBuilder sb = new StringBuilder();
        Date to = new Date();
        Date from = new Date(System.currentTimeMillis() - 24L * 60 * 60 * 1000 * 90);
        List<TransactionEntity> transactionEntities = findBetweenDateAndCustomerId(from, to, customerId);
        int totalRewardsAmt = 0;
        for (TransactionEntity transactionEntity : transactionEntities) {
            int rewardsAmt = calculateRewards(transactionEntity.getAmount());
            totalRewardsAmt += rewardsAmt;
            sb.append("Transaction created at: ").append(transactionEntity.getDate())
                    .append(" with amount: $")
                    .append(transactionEntity.getAmount())
                    .append(" -> ").append(rewardsAmt)
                    .append(" pts generated;");
        }
        sb.append(" *** The total reward pts the customer get for the last 90 days are: ").append(totalRewardsAmt).append(" ***");
        return sb.toString();
    }

    @Override
    public List<TransactionEntity> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void saveATransaction(TransactionEntity transactionEntity) {
        transactionRepository.saveAndFlush(transactionEntity);
    }

    @Override
    public void deleteATransaction(Long tId) {
        transactionRepository.deleteATransaction(tId);
    }

//    @Override
//    public TransactionEntity findATransactionById(Long tId) {
//        return transactionRepository.findBy(ttId);
//    }

    public int calculateRewards(double rewardsAmt) {
        if (rewardsAmt <= 50) return 0;
        else if (rewardsAmt <= 100) return (int) (rewardsAmt - 50);
        else return (int) (50 + 2 * (rewardsAmt - 100));
    }
}
