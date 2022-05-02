package com.axeman.auth.security.utils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class HashUtils {

    private static final Integer DEFAULT_SALT_SIZE = 16;

    public static byte[] generateSalt(int byteSize) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[byteSize];
        secureRandom.nextBytes(bytes);
        return base64Encode(bytes);
    }

    public static byte[] generateSalt() { return generateSalt(DEFAULT_SALT_SIZE); }

    public static byte[] base64Encode(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    public static byte[] base64Decode(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }

    public static String getString(byte[] bytes) { return new String(bytes, StandardCharsets.UTF_8); }

    public static byte[] getBytes(String string) { return string.getBytes(StandardCharsets.UTF_8); }
}
