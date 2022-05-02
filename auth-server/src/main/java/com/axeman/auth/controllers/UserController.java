package com.axeman.auth.controllers;

import com.axeman.auth.entities.User;
import com.axeman.auth.models.http.request.SaveUserRequest;
import com.axeman.auth.services.UserAccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping()
    public User createUser(@RequestBody SaveUserRequest userRequest) {
        log.debug("UserController::createUser new user requested: {}", userRequest.getUsername());
        return userAccountService.createAccount(userRequest);
    }
}
