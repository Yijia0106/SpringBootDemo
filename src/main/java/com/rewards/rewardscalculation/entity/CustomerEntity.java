package com.rewards.rewardscalculation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="customers")
public class CustomerEntity {

    public CustomerEntity(){

    }

    public CustomerEntity(Long customer_id, String firstName, String lastName, Date registeredDate){
        super();
        this.customerId = customer_id;
        this.fistName = firstName;
        this.lastName = lastName;
        this.registeredDate = registeredDate;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", updatable = false, nullable = false)
    @Getter
    @Setter
    private Long customerId;

    @Setter
    @Column(name = "registered_date")
    private Date registeredDate;

    @Setter
    @Getter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "fist_name")
    private String fistName;

    @Getter
    @Setter
    @Column(name = "is_valid")
    private Character isValid;

}
