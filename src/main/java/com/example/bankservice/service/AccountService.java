package com.example.bankservice.service;

import com.example.bankservice.model.dto.DepositDto;
import com.example.bankservice.model.dto.TransferDto;
import com.example.bankservice.model.dto.WithdrawDto;
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

    @Transactional
    public Account mapMember (Account account) {

        // account 객체로 넘어온 member Id값으로 member 조회후, account를 추가해서 두객체 save
        Optional<Member> member = memberRepository.findById(account.getMemberId());
        Optional<Account> account_ = accountRepository.findById(account.getAccountNumber());

        if (member.isPresent() && account_.isPresent()) {
            Member m = member.get();
            Account a = account_.get();

            if (m.getAccounts().equals("")) {
                m.setAccounts(String.valueOf(account.getAccountNumber()));
            }else {
                m.setAccounts(m.getAccounts() + "," + String.valueOf(account.getAccountNumber()));
            }
            a.setMemberId(account.getMemberId());
            memberRepository.save(m);
            return accountRepository.save(a);
        }
        return null;
    }

    @Transactional
    public Account deposit (DepositDto depositDto) {
        Account account = accountRepository.findById(depositDto.getAccountNumber()).get();
        account.setBalance(depositDto.getAmount() + account.getBalance());
        accountRepository.save(account);
        MemberBalanceHistory history = new MemberBalanceHistory(TransType.DEPOSIT,account.getMemberId(), depositDto.getAmount());
        memberBalanceHistoryRepository.save(history);
        return account;

    }

    @Transactional
    public Account withdraw (WithdrawDto withdrawDto) {
        Account account = accountRepository.findById(withdrawDto.getAccountNumber()).get();
        account.setBalance(account.getBalance() - withdrawDto.getAmount());
        accountRepository.save(account);
        memberBalanceHistoryRepository.save(new MemberBalanceHistory(TransType.WITHDRAW,account.getMemberId(), withdrawDto.getAmount()));
        return account;
    }

    @Transactional
    public Account transfer (TransferDto transferDto) {

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
