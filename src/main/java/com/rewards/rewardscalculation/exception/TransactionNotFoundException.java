package com.rewards.rewardscalculation.exception;

import lombok.Getter;

@Getter
public class TransactionNotFoundException extends RuntimeException {
    private final String errorMsg;

    public TransactionNotFoundException(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}