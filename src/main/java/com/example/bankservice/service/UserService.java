package com.example.bankservice.service;

import com.example.bankservice.entity.User;
import com.example.bankservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signup(User user) {
        return userRepository.save(user);

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


}
