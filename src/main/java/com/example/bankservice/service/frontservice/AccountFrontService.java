package com.example.bankservice.service.frontservice;

import com.example.bankservice.model.entity.Account;
import com.example.bankservice.model.restresult.RestResult;
import com.example.bankservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashMap;

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


    public RestResult getAll(Pageable pageable) {
        Page<Account> accounts = accountService.getAll(pageable);

        RestResult restResult = RestResult.success();
        restResult.addData("accounts",accounts.getContent());
        restResult.addPage(accounts.getTotalPages(),accounts.getTotalElements(),accounts.getNumber(), accounts.getSize());

        return restResult;
    }

    public RestResult mapMember(Account account) {
        Account m = accountService.mapMeber(account);
        RestResult restResult = RestResult.success();
        restResult.addData("account",account);
        return restResult;
    }
}
