package com.axeman.auth.services;

import com.axeman.auth.entities.User;
import com.axeman.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    UserRepository userRepository;

    public User createAccount(User user) {
        return userRepository.save(user);
    }
}
