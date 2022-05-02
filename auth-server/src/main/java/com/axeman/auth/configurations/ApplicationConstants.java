package com.axeman.auth.configurations;

public interface ApplicationConstants {
    String EXCEPTION_MSG_SALT_NOT_NULL = "Salt value should not be passed on new user entity";
    String EXCEPTION_MSG_PASSWORD_NOT_NULL = "Plain text password should not be set on user entity";
    String EXCEPTION_MSG_USER_ALREADY_EXISTS = "User with given username already exists";
    String EXCEPTION_MSG_USER_OBJECT_NULL = "User request mapping invalid. Got null user object";
}
