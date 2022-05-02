package com.axeman.auth.security.custom.hash;

import com.axeman.auth.security.custom.hash.impl.Argon2id;
import com.axeman.auth.security.utils.HashUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class Argon2idTests {

    private static final String MESSAGE = "password";
    private static final String MESSAGE_MATCH = "password";
    private static final String MESSAGE_MISMATCH = "invalid-password";
    private static final Integer SALT_SIZE = 16;

    private MessageDigest messageDigest;
    private byte[] salt;

    @BeforeEach
    protected void setup() {
        messageDigest = new Argon2id();
        salt = HashUtils.generateSalt(SALT_SIZE);
    }

    @Test
    void givenMessage_usingArgon2idHashingMethod_shouldReturnValidHash() {
        byte[] expectedHash = HashUtils.base64Decode(messageDigest.generateHash(MESSAGE, salt));
        assertEquals(32, expectedHash.length);
    }

    @Test
    void givenSameMessageString_usingArgon2idHashingMethod_shouldGenerateSameHash() {
        String expectedHash = HashUtils.getString(messageDigest.generateHash(MESSAGE, salt));
        String matchingHash = HashUtils.getString(messageDigest.generateHash(MESSAGE_MATCH, salt));
        assertEquals(expectedHash, matchingHash);
    }

    @Test
    void givenDifferentMessageString_usingArgon2idHashingMethod_shouldGenerateDifferentHash() {
        String expectedHash = HashUtils.getString(messageDigest.generateHash(MESSAGE, salt));
        String mismatchedHash = HashUtils.getString(messageDigest.generateHash(MESSAGE_MISMATCH, salt));
        assertNotEquals(expectedHash, mismatchedHash);
    }
}


