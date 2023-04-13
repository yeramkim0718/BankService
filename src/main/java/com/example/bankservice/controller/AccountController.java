package com.example.bankservice.controller;

import com.example.bankservice.entity.Account;
import com.example.bankservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.register(account), HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<Page<Account>> list (Pageable pageable) {
        return new ResponseEntity<>(accountService.getAll(pageable),HttpStatus.OK);
    }

}
