package com.example.bankservice.controller;

import com.example.bankservice.model.dto.DepositDto;
import com.example.bankservice.model.dto.TransferDto;
import com.example.bankservice.model.dto.WithdrawDto;
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

    @PostMapping("/deposit")
    public RestResult deposit(@RequestBody DepositDto depositDto) {
        return accountFrontService.deposit(depositDto);
    }

    @PostMapping("withdraw")
    public RestResult withdraw (@RequestBody WithdrawDto withdrawDto) {
        return accountFrontService.withdraw(withdrawDto);

    }

    @PostMapping("transfer")
    public RestResult transfer (@RequestBody TransferDto transferDto) {
        return accountFrontService.transfer(transferDto);

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
