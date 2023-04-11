package com.example.bankservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
public class Account {
    @Id
    private Integer accountNumber;
    private Integer balance;

}
