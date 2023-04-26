package com.example.bankservice.service;

import com.example.bankservice.exception.BankAccountNotFoundException;
import com.example.bankservice.exception.BankRuntimeException;
import com.example.bankservice.model.dto.DepositParam;
import com.example.bankservice.model.dto.TransferParam;
import com.example.bankservice.model.dto.WithdrawParam;
import com.example.bankservice.model.entity.Account;
import com.example.bankservice.model.entity.Member;
import com.example.bankservice.model.entity.MemberBalanceHistory;
import com.example.bankservice.model.entity.TransType;
import com.example.bankservice.repository.AccountRepository;
import com.example.bankservice.repository.MemberBalanceHistoryRepository;
import com.example.bankservice.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberBalanceHistoryRepository memberBalanceHistoryRepository;

    public Account register(Account account) {

        return accountRepository.save(account);
    }

    public void testException() {
        try {
            new FileInputStream(new File("sdfd"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void testRuntimeException() {
        new RuntimeException("asdfsdf");
    }

    @Transactional
    public void mapMember(Account account) {

        // account 객체로 넘어온 member Id값으로 member 조회후, account를 추가해서 두객체 save
        Optional<Member> memberOptional = memberRepository.findById(account.getMemberId());
        Optional<Account> account_ = accountRepository.findById(account.getAccountNumber());

        try {
            if (memberOptional.isPresent() && account_.isPresent()) {
                Member m = memberOptional.get();
                Account a = account_.get();

                if (m.getAccounts().equals("")) {
                    m.setAccounts(String.valueOf(account.getAccountNumber()));
                }else {
                    m.setAccounts(m.getAccounts() + "," + String.valueOf(account.getAccountNumber()));
                }
                a.setMemberId(account.getMemberId());
                memberRepository.save(m);
                accountRepository.save(a);
            }
        }catch (Exception e) {
            throw new BankRuntimeException("error", "adf");
        }
    }

    @Transactional
    public Account deposit (DepositParam depositParam) {
        Account account = accountRepository.findById(depositParam.getAccountNumber()).get();
        account.setBalance(depositParam.getAmount() + account.getBalance());
        accountRepository.save(account);
        MemberBalanceHistory history = new MemberBalanceHistory(TransType.DEPOSIT,account.getMemberId(), depositParam.getAmount());
        memberBalanceHistoryRepository.save(history);
        return account;

    }

    @Transactional
    public Account withdraw (WithdrawParam withdrawDto) {
        Account account = accountRepository.findById(withdrawDto.getAccountNumber()).get();
        account.setBalance(account.getBalance() - withdrawDto.getAmount());
        accountRepository.save(account);
        memberBalanceHistoryRepository.save(new MemberBalanceHistory(TransType.WITHDRAW,account.getMemberId(), withdrawDto.getAmount()));
        return account;
    }

    @Transactional
    public Account transfer (TransferParam transferDto) {

        Account fromAccount = accountRepository.findById(transferDto.getFromAccountNumber()).get();
        Account toAccount = accountRepository.findById(transferDto.getToAccountNumber()).get();
        fromAccount.setBalance(fromAccount.getBalance() - transferDto.getAmount());
        toAccount.setBalance(toAccount.getBalance() + transferDto.getAmount());
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        memberBalanceHistoryRepository.save(new MemberBalanceHistory(TransType.TRANSFER,fromAccount.getMemberId(), toAccount.getMemberId(), transferDto.getAmount()));

        return fromAccount;

    }

    public Page<Account> getAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

}
