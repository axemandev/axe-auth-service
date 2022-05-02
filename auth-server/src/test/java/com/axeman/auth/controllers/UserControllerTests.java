package com.axeman.auth.controllers;

import com.axeman.auth.entities.User;
import com.axeman.auth.models.http.request.SaveUserRequest;
import com.axeman.auth.mappers.UserMapper;
import com.axeman.auth.services.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
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

    private SaveUserRequest userRequest;

    @BeforeEach
    public void setup() {
        userRequest = SaveUserRequest.builder()
                .username("test-user")
                .password("test-password")
                .build();
        User user = Mappers.getMapper(UserMapper.class).userRequestToUserEntity(userRequest);
        when(userAccountService.createAccount(any(SaveUserRequest.class))).thenReturn(user);
    }

    @Test
    public void givenUserDetails_whenCallingCreateUserViaController_shouldCreateNewUser() {
        User newUser = userController.createUser(userRequest);
        assertEquals("test-user", newUser.getUsername());
    }
}
