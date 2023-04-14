package com.example.bankservice.service.frontservice;

import com.example.bankservice.model.dto.DepositDto;
import com.example.bankservice.model.dto.WithdrawDto;
import com.example.bankservice.model.entity.Account;
import com.example.bankservice.model.restresult.RestResult;
import com.example.bankservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountFrontService {

    @Autowired
    AccountService accountService;

    public RestResult register (Account account) {

        Account saved = accountService.register(account);

        RestResult restResult = RestResult.success();
        restResult.addData("registered" , saved);

        return restResult;

    }

    public RestResult deposit (DepositDto depositDto) {
        Account deposited = accountService.deposit(depositDto);

        RestResult restResult = RestResult.success();

        return restResult.addData("deposited",deposited);
    }

    public RestResult withdraw (WithdrawDto withdrawDto) {
        Account withdrawed = accountService.withdraw(withdrawDto);

        RestResult restResult = RestResult.success();

        return restResult.addData("withdrawed",withdrawed);

    }


    public RestResult getAll(Pageable pageable) {
        Page<Account> accounts = accountService.getAll(pageable);

        RestResult restResult = RestResult.success();
        restResult.addData("accounts",accounts.getContent());
        restResult.addPage(accounts.getTotalPages(),accounts.getTotalElements(),accounts.getNumber(), accounts.getSize());

        return restResult;
    }

    public RestResult mapMember(Account account) {
        Account m = accountService.mapMember(account);
        RestResult restResult = RestResult.success();
        restResult.addData("account",account);
        return restResult;
    }
}
