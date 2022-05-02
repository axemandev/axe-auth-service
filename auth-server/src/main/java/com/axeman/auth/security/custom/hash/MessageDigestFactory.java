package com.axeman.auth.security.custom.hash;

import com.axeman.auth.security.custom.hash.impl.Argon2id;

public class MessageDigestFactory {

    private MessageDigestFactory() {
    }

    public static MessageDigest getMessageDigest(String method) {
        switch (method) {
            case MDRegistry.ARGON_2ID:
                return new Argon2id();
            default:
                return null;
        }
    }
}
