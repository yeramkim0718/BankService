package com.example.bankservice.service;

import com.example.bankservice.entity.Account;
import com.example.bankservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account register(Account account) {
        return accountRepository.save(account);

    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

}
