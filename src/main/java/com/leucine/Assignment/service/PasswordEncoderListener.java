package com.leucine.Assignment.service;

import com.leucine.Assignment.ApplicationContextProvider;
import com.leucine.Assignment.dao.User;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class PasswordEncoderListener {

    @PrePersist
    @PreUpdate
    public void encodePassword(Object object) {
        if (object instanceof User user) {
            ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
            PasswordEncoder passwordEncoder = ctx.getBean(PasswordEncoder.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
}