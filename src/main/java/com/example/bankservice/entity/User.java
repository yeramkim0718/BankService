package com.example.bankservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name="user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String accounts;

    /*@OneToMany (cascade = CascadeType.ALL, mappedBy = "account")
    private List<Account> accounts = new ArrayList<Account>();*/
}
