package com.example.bankservice.service.frontservice;

import com.example.bankservice.exception.BankRuntimeException;
import com.example.bankservice.model.dto.DepositParam;
import com.example.bankservice.model.dto.TransferParam;
import com.example.bankservice.model.dto.WithdrawParam;
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

    public RestResult deposit (DepositParam depositParam) {
        Account deposited = accountService.deposit(depositParam);

        RestResult restResult = RestResult.success();

        return restResult.addData("deposited",deposited);
    }

    public RestResult withdraw (WithdrawParam withdrawParam) {
        Account withdrawed = accountService.withdraw(withdrawParam);

        // @Async, 비동기: fire & forget
        sendEmail(); // 이메일 전송 요청 접수

        RestResult restResult = RestResult.success();

        return restResult.addData("withdrawed",withdrawed);

    }

    /**
     * 이메일 전송 코드 블럭
     */
    ///// 이메일을 보내는데 언제나 1분 10초가 걸린다.
//    @Async // 별도의 다른 쓰레드 풀을 사용한다.
    // todo mq 를 활용한다.
    public void sendEmail() {
        // queue : fifo
        // 이메일 전송 요청 ==> [1000개] ==> (이메일 전송하기) * 10의 컨슈머
        // 타임세일 구매요청 ==> [요청 10만개의 구매요청] ==> 1개의 구매처리기 (tx: 10개까지만 꺼내고 완료처리하고 그 뒤는 에러처리)
    }

    /**
     * http server 3대 (서버 대수 만큼 인메모리 캐시가 있다.) 서로달라. 3벌.
     * mysql 서버 1대 (disk i/o 로 인해서 느림, 인덱스를 사용하던지 안하던지.) --> 빠르게 하는 방법은 인메모리가 필요.
     * redis 서버 1대 (분산캐시용)
     * 인메모리 안에 들어가는 내용은 ???
     *      *  --> api 호출결과,
     *      *  --> key, value 쌍
     *      *  --> parameter list, 호출결과 (json) --> 조회성만.  ttl(time to live : 10초)
     *
     * 1. 부하분산 => 클러스터링 (redis 를 늘린다. 동기화이슈가 생김. ) 3대로 둔다. 마스터 슬레이브 구조로, 마스터만 r/w, 슬레이브는 r only
     * 2. 샤딩 => 서로 나눠서 처리. (0~9번으로 시작하는 것으로 10개의 샤드를 구성할 수 있음)
     *    0~9, 0번으로 시작하는 얘는 0번 레디스가 처리
     *    0~9, 1번으로 시작하는 얘는 1번 레디스가 처리
     *    ...
     *    0~9, 9번으로 시작하는 얘는 9번 레디스가 처리
     * 3. 스프링의 캐시 추상화 기술. --> AOP 매커니즘 확인하기.
     */

    public RestResult transfer(TransferParam transferParam) {
        Account transfered = accountService.transfer(transferParam);

        RestResult restResult = RestResult.success();

        return restResult.addData("transfered",transfered);
    }


    public RestResult getAll(Pageable pageable) {
        Page<Account> accounts = accountService.getAll(pageable);

        RestResult restResult = RestResult.success();
        restResult.addData("accounts",accounts.getContent());
        restResult.addPage(accounts.getTotalPages(),accounts.getTotalElements(),accounts.getNumber(), accounts.getSize());

        return restResult;
    }

    public RestResult mapMember(Account account) {
        try {
            accountService.mapMember(account);
        }catch (BankRuntimeException e) {
            // 실패리턴. !
        }
        RestResult restResult = RestResult.success();
        restResult.addData("account",account);
        return restResult;
    }
}
