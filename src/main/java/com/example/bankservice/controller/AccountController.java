package com.example.bankservice.controller;

import com.example.bankservice.model.dto.DepositParam;
import com.example.bankservice.model.dto.TransferParam;
import com.example.bankservice.model.dto.WithdrawParam;
import com.example.bankservice.model.entity.Account;
import com.example.bankservice.model.restresult.RestResult;
import com.example.bankservice.service.frontservice.AccountFrontService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 400 에러: --> 클라이언트의 잘못이야
 *  - 400 에러:
 *  - 401 에러: 인증요류 (로그인필수)
 *  - 403 에러: 권한오류
 *
 * 400 에러로 리턴:
 *
 * 500 에러: --> 서버의 잘못이야
 *
 * 500 에러로 리턴:
 */
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountFrontService accountFrontService;

    // 유저와 계좌를 할당하기 API

    /**
     * 에러케이스1: account 가 존재하지 않는경우
     *
     * 400 에러로,
     * {
     *     "id":"not_found",
     *     "message":"존재하지 않는 유저입니다."
     * }
     *
     * 500 에러로, ==> 서버의 잘못. --> 로직의잘못, --> CS 요쳥. .....
     * {
     *     "id":"not_found",
     *     "message":"존재하지 않는 유저입니다."
     * }
     * @param account
     * @return
     */
    @PostMapping ("/mapMember")
    public RestResult mapMember (@RequestBody Account account) {
        return accountFrontService.mapMember(account);
    }

    @PostMapping("/deposit")
    @ResponseStatus
    public RestResult deposit(@RequestBody DepositParam depositParam) {
        return accountFrontService.deposit(depositParam);
    }

    @PostMapping("withdraw")
    public RestResult withdraw (@RequestBody WithdrawParam withdrawDto) {
        return accountFrontService.withdraw(withdrawDto);

    }

    @PostMapping("transfer")
    public RestResult transfer (@RequestBody TransferParam transferDto) {
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
