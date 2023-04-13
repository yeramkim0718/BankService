package com.example.bankservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account {
    @Id @Column(name="account_number")
    private Integer accountNumber;
    private Integer balance = 0;
    @Column(name = "user_id")
    private String userId;

}
