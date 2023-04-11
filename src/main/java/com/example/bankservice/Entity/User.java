package com.example.bankservice.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<Account>();
}
