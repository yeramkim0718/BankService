package com.example.bankservice.service;

import com.example.bankservice.model.entity.Member;
import com.example.bankservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member signup(Member member) {
        return memberRepository.save(member);

    }

    public Page<Member> getAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }


}
