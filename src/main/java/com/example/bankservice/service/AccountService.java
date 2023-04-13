package com.example.bankservice.service;

import com.example.bankservice.model.entity.Account;
import com.example.bankservice.model.entity.Member;
import com.example.bankservice.repository.AccountRepository;
import com.example.bankservice.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Account register(Account account) {

        // account 객체로 넘어온 member Id값으로 member 조회후, account를 추가해서 두객체 save
        Optional<Member> member = memberRepository.findById(account.getMemberId());
        if (member.isPresent()) {
            Member m = member.get();
            m.setAccounts(m.getAccounts() + "," + account.getAccountNumber());
            memberRepository.save(m);
            return accountRepository.save(account);
        }

        return account;
    }

    public Page<Account> getAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

}
