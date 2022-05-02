package com.axeman.auth.services;

import com.axeman.auth.entities.User;
import com.axeman.auth.exceptions.UserCreationException;
import com.axeman.auth.models.http.request.SaveUserRequest;
import com.axeman.auth.mappers.UserMapper;
import com.axeman.auth.repositories.UserRepository;
import com.axeman.auth.security.custom.hash.MessageDigest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserAccountServiceTests {

    private static final String USERNAME = "test-user";
    private static final String PASSWORD = "password";
    private static final String SALT = "salt";

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageDigest argon2idMessageDigest;

    @InjectMocks
    private UserAccountService userAccountService;

    private SaveUserRequest userRequest;

    @BeforeEach
    public void setup() {
        userRequest = SaveUserRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
        User user = Mappers.getMapper(UserMapper.class).userRequestToUserEntity(userRequest);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findByUsername(USERNAME)).thenReturn(user);

        byte[] randomBytes = new byte[32];
        when(argon2idMessageDigest.generateHash(any(String.class), any(byte[].class))).thenReturn(randomBytes);
    }

    @Test
    public void givenUserDetails_whenNew_shouldCreateNewAccount() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(null);
        User newUserAccount = userAccountService.createAccount(userRequest);
        assertEquals(USERNAME, newUserAccount.getUsername());
    }

    @Test
    public void givenUserDetails_whenAlreadyExists_shouldThrowUserExistsException() {
        assertThrows(UserCreationException.class, () -> userAccountService.createAccount(userRequest));
    }
}
