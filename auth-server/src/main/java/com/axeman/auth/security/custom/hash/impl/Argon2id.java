package com.axeman.auth.security.custom.hash.impl;

import com.axeman.auth.security.custom.hash.MessageDigest;
import com.axeman.auth.security.utils.HashUtils;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

@Log4j2
public class Argon2id implements MessageDigest {

    private static final Integer OUTPUT_LENGTH = 32;
    private static final Integer PARALLELISM = 1;
    private static final Integer ITERATIONS = 4;
    private static final Integer MEMORY_LIMIT_IN_KB = 64 * 1024;

    @Override
    public byte[] generateHash(String message, byte[] salt) {
        byte[] result = new byte[OUTPUT_LENGTH];

        Argon2BytesGenerator argon2 = new Argon2BytesGenerator();
        argon2.init((new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id))
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withSalt(salt)
                .withParallelism(PARALLELISM)
                .withIterations(ITERATIONS)
                .withMemoryAsKB(MEMORY_LIMIT_IN_KB)
                .build());

        Instant start = Instant.now();
        argon2.generateBytes(message.getBytes(StandardCharsets.UTF_8), result, 0, result.length);
        Instant end = Instant.now();
        log.info("Argon2id::generateHash- took {} ms", Duration.between(start, end).getNano()/1000000);

        return HashUtils.base64Encode(result);
    }
}
