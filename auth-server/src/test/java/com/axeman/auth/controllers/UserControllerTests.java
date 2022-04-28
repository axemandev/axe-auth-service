package com.axeman.auth.controllers;

import com.axeman.auth.entities.User;
import com.axeman.auth.models.UserAccountStatus;
import com.axeman.auth.services.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private UserController userController;

    private User testUser;

    @BeforeEach
    public void setup() {
        testUser = User.builder()
                .username("test-user")
                .password("test-password")
                .status(UserAccountStatus.ACTIVE)
                .build();
        when(userAccountService.createAccount(any(User.class))).thenReturn(testUser);
    }

    @Test
    public void givenUserDetails_whenCallingCreateUserViaController_shouldCreateNewUser() {
        User newUser = userController.createUser(testUser);
        assertEquals("test-user", newUser.getUsername());
        assertEquals(UserAccountStatus.ACTIVE, newUser.getStatus());
    }
}
