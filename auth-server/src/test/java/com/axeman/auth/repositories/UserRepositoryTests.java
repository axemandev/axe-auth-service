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

    @Autowired
    private UserRepository userRepository;

    User testUser;

    @BeforeEach
    void setup() {
        testUser = User.builder()
                .username("test-user")
                .password("test-password")
                .status(UserAccountStatus.ACTIVE)
                .primaryEmail("test-email@gmail.com")
                .build();
    }

    @Test
    void shouldCreateNewUserAccount() {
        User newUser = userRepository.save(testUser);
        assertEquals("test-user", newUser.getUsername());
    }

}