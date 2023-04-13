package com.example.bankservice.repository;

import com.example.bankservice.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest() {
        Member member = new Member();
        member.setName("name1");

        Member savedMember = memberRepository.save(member);

        Assertions.assertEquals("name1", savedMember.getName());
    }

}
