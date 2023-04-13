package com.example.bankservice.service.frontservice;

import com.example.bankservice.model.entity.Member;
import com.example.bankservice.model.restresult.RestResult;
import com.example.bankservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MemberFrontService {

    @Autowired
    MemberService memberService;

    public RestResult signup(Member member) {
        Member saved = memberService.signup(member);
        RestResult restResult = RestResult.success();
        restResult.addData("member",saved);
        return restResult;
    }

    public RestResult getAll(Pageable pageable) {
        Page<Member> members = memberService.getAll(pageable);

        RestResult restResult = RestResult.success();
        restResult.addData("members",members.getContent());
        restResult.addPage(members.getTotalPages(), members.getTotalElements(),members.getNumber(), members.getSize());

        return restResult;

    }

}
