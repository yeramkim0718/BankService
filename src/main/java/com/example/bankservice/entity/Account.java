package com.example.bankservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "account")
@NoArgsConstructor
public class Account {
    @Id @Column(name="account_number")
    private Integer accountNumber;
    private Integer balance = 0;
    @Column(name = "member_id")
    private Integer memberId;

}
