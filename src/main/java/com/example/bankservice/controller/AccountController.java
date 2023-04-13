package com.example.bankservice.controller;

import com.example.bankservice.model.entity.Account;
import com.example.bankservice.model.restresult.RestResult;
import com.example.bankservice.service.AccountService;
import com.example.bankservice.service.frontservice.AccountFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountFrontService accountFrontService;

    @PostMapping("/register")
    public RestResult register(@RequestBody Account account) {

        return accountFrontService.register(account);

    }

    @GetMapping("/list")
    public RestResult list (Pageable pageable) {
        return accountFrontService.getAll(pageable);
    }

}
