package com.example.bankservice.controller;

import com.example.bankservice.model.entity.Member;
import com.example.bankservice.model.restresult.RestResult;
import com.example.bankservice.service.MemberService;
import com.example.bankservice.service.frontservice.MemberFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberFrontService memberFrontService;

    @PostMapping("/signup")
    public RestResult signup(@RequestBody Member member) {
        return memberFrontService.signup(member);

    }

    @GetMapping ("/list")
    public RestResult list(Pageable pageable) {
        return memberFrontService.getAll(pageable);
    }

}
