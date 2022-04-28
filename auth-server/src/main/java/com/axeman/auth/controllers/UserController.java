package com.axeman.auth.controllers;

import com.axeman.auth.entities.User;
import com.axeman.auth.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userAccountService.createAccount(user);
    }
}
