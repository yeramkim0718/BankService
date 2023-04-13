package com.example.bankservice.controller;

import com.example.bankservice.entity.User;
import com.example.bankservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return new ResponseEntity<User>(userService.signup(user), HttpStatus.OK);

    }

    @GetMapping ("/list")
    public ResponseEntity<List<User>> list () {
        return new ResponseEntity<List<User>>(userService.getAll(),HttpStatus.OK);
    }

}
