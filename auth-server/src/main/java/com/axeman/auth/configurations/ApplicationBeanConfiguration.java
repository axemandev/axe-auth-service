package com.axeman.auth.configurations;

import com.axeman.auth.security.custom.hash.MDRegistry;
import com.axeman.auth.security.custom.hash.MessageDigest;
import com.axeman.auth.security.custom.hash.MessageDigestFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public MessageDigest argon2idMessageDigest() {
        return MessageDigestFactory.getMessageDigest(MDRegistry.ARGON_2ID);
    }
}
