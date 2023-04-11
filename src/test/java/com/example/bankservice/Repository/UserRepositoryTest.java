package com.example.bankservice.Repository;

import com.example.bankservice.Entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest() {
        User user = User.builder()
                .name("name1")
                .build();

        User savedUser = userRepository.save(user);

        Assertions.assertEquals("name1", savedUser.getName());
    }

}
