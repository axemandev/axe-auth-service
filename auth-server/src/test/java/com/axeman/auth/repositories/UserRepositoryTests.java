package com.axeman.auth.repositories;

import com.axeman.auth.entities.User;
import com.axeman.auth.models.UserAccountStatus;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTests {

    private static final String USERNAME = "test-user";
    private static final String PASSWORD = "password";
    private static final String SALT = "salt";
    private static final String EMAIL = "test-email@invalid.com";
    private static final String NON_EXISTENT_USERNAME = "non-existent-user";

    @Autowired
    private UserRepository userRepository;

    User testUser;

    @BeforeEach
    void setup() {
        testUser = User.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .salt(SALT)
                .status(UserAccountStatus.ACTIVE)
                .primaryEmail(EMAIL)
                .build();
    }

    @Test
    void shouldCreateNewUserAccount() {
        User newUser = userRepository.save(testUser);
        assertEquals(USERNAME, newUser.getUsername());
    }

    @Test
    void givenUsername_whenExists_shouldReturnUserRecord() {
        User newUser = userRepository.save(testUser);
        User fetchedUser = userRepository.findByUsername(USERNAME);
        assertTrue(fetchedUser.getUsername().equals(newUser.getUsername()));
    }

    @Test
    void givenUsername_whenNotExists_shouldReturnNull() {
        User fetchedUser = userRepository.findByUsername(NON_EXISTENT_USERNAME);
        assertNull(fetchedUser);
    }

}