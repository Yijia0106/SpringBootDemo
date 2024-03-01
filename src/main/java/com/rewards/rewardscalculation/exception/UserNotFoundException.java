package com.rewards.rewardscalculation.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final String errorMsg;

    public UserNotFoundException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
