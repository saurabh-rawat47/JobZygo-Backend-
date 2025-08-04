package com.tony.JobZygo.controller;

import com.tony.JobZygo.entity.User;
import com.tony.JobZygo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/auth/signup")
    public void signup(@RequestBody User user) {
        userService.signup(user);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);


    }

}
