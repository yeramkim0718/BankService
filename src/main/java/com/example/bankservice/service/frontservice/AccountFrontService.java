package com.example.bankservice.service.frontservice;

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

    public RestResult getAll(Pageable pageable) {
        Page<Account> accounts = accountService.getAll(pageable);
        RestResult restResult = RestResult.success();
        restResult.addData("page",accounts.get());
        restResult.addData("pagenum",accounts.getTotalPages());
        return restResult;
    }
}
