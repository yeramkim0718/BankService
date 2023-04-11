package com.example.bankservice.Repository;

import com.example.bankservice.Entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest() {
        User user = new User();
        user.setName("name1");

        User savedUser = userRepository.save(user);

        Assertions.assertEquals("name1", savedUser.getName());
    }

}
