package com.example.bankservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name="member")
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String accounts;

    /*@OneToMany (cascade = CascadeType.ALL, mappedBy = "account")
    private List<Account> accounts = new ArrayList<Account>();*/
}