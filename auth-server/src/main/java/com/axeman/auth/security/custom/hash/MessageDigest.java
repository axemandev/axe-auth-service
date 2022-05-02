package com.axeman.auth.security.custom.hash;

public interface MessageDigest {
    public byte[] generateHash(String message, byte[] salt);
}
