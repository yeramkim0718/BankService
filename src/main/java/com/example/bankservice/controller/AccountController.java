package com.example.bankservice.controller;

import com.example.bankservice.entity.Account;
import com.example.bankservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        account.setAccountNumber(1234);
        return new ResponseEntity<>(accountService.register(account), HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<List<Account>> list () {
        return new ResponseEntity<>(accountService.getAll(),HttpStatus.OK);
    }

}
