package com.axeman.auth.services;

import com.axeman.auth.configurations.ApplicationConstants;
import com.axeman.auth.entities.User;
import com.axeman.auth.exceptions.UserCreationException;
import com.axeman.auth.mappers.UserMapper;
import com.axeman.auth.models.UserAccountStatus;
import com.axeman.auth.models.http.request.SaveUserRequest;
import com.axeman.auth.repositories.UserRepository;
import com.axeman.auth.security.custom.hash.MessageDigest;
import com.axeman.auth.security.utils.HashUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

@Service
public class UserAccountService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageDigest argon2idMessageDigest;

    public User createAccount(SaveUserRequest userRequest) {
        User user = getUserFromRequest(userRequest);
        validateUser(user);

        byte[] salt = HashUtils.generateSalt();
        byte[] passwordHash = argon2idMessageDigest.generateHash(userRequest.getPassword(), salt);

        user.setSalt(HashUtils.getString(salt));
        user.setPassword(HashUtils.getString(passwordHash));
        user.setPasswordCreationDate(Date.from(Instant.now()));
        user.setStatus(UserAccountStatus.ACTIVE);

        return userRepository.save(user);
    }

    private User getUserFromRequest(SaveUserRequest userRequest) {
        UserMapper mapper = Mappers.getMapper(UserMapper.class);
        return mapper.userRequestToUserEntity(userRequest);
    }

    private void validateUser(User user) {
        assertUserNotNull(user);
        assertNullPassword(user);
        assertNullSalt(user);
        assertUserDoesNotExistInDatabase(user);
    }

    private void assertUserNotNull(User user) {
        if (user == null) {
            throw new UserCreationException(ApplicationConstants.EXCEPTION_MSG_USER_OBJECT_NULL);
        }
    }

    private void assertNullPassword(User user) {
        if (user == null || user.getPassword() != null) {
            throw new UserCreationException(ApplicationConstants.EXCEPTION_MSG_PASSWORD_NOT_NULL);
        }
    }

    private void assertNullSalt(User user) {
        if (user.getSalt() != null) {
            throw new UserCreationException(ApplicationConstants.EXCEPTION_MSG_SALT_NOT_NULL);
        }
    }

    private void assertUserDoesNotExistInDatabase(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserCreationException(ApplicationConstants.EXCEPTION_MSG_USER_ALREADY_EXISTS);
        }
    }
}
