package com.example.bankservice.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<Account>();

}
