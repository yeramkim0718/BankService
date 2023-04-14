package com.example.bankservice.controller;

import com.example.bankservice.model.dto.DepositDto;
import com.example.bankservice.model.entity.Account;
import com.example.bankservice.model.restresult.RestResult;
import com.example.bankservice.service.frontservice.AccountFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountFrontService accountFrontService;

    // 유저와 계좌를 할당하기 API
    @PostMapping ("/mapMember")
    public RestResult mapMember (@RequestBody Account account) {
        return accountFrontService.mapMember(account);
    }

    // 유저의 계좌 잔고에 입금하기 API
    /*
    1. 유저와 계좌를 할당하기 API
    2. 유저의 계좌 잔고에 입금하기 API
    3. 유저의 계좌 잔고에서 이체하기 API
    4. 유저의 계좌 잔고에서 인출하기 API
     */
    @PostMapping("/deposit")
    public RestResult deposit(@RequestBody DepositDto depositDto) {
        return accountFrontService.deposit(depositDto);
    }
    @PostMapping("/register")
    public RestResult register(@RequestBody Account account) {

        return accountFrontService.register(account);

    }

    @GetMapping("/list")
    public RestResult list (Pageable pageable) {
        return accountFrontService.getAll(pageable);
    }

}
