package com.example.bankservice.controller;

import com.example.bankservice.model.entity.Member;
import com.example.bankservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.signup(member), HttpStatus.OK);

    }

    @GetMapping ("/list")
    public ResponseEntity<Page<Member>> list(Pageable pageable) {
        return new ResponseEntity<>(memberService.getAll(pageable),HttpStatus.OK);
    }

}
