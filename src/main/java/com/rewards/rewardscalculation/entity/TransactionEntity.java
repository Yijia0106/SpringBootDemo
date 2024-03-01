package com.rewards.rewardscalculation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    public TransactionEntity() {

    }

    public TransactionEntity(Long tId, Long customerId, double amount, Date date) {
        super();
        this.tId = tId;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "t_id", updatable = false, nullable = false)
    @Setter
    @Getter
    private Long tId;

    @Setter
    @Getter
    @Column(name = "created_date")
    @NotNull
    private Date date;

    @Setter
    @Getter
    @NotNull
    @Column(name = "customer_id")
    private Long customerId;

    @Setter
    @Getter
    @NotNull
    @Column(name = "amount")
    @Positive
    private double amount;

}
