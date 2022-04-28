package com.axeman.auth.services;

import com.axeman.auth.entities.User;
import com.axeman.auth.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @Mock
    UserRepository userRepository;

    UserAccountService userAccountService;

    User testUser;

    @BeforeEach
    public void setup() {
        testUser = new User();
        userAccountService = new UserAccountService();
        when(userRepository.save(any(User.class))).thenReturn(testUser);
    }

    @Test
    public void givenNewAccountDetails_shouldCreateNewAccount() {
        User newUserAccount = userAccountService.createAccount(testUser);
        assertEquals(testUser.getUsername(), newUserAccount.getUsername(), "Account username mismatch");
    }
}
