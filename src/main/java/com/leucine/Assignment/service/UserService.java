package com.leucine.Assignment.service;

import com.leucine.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.hibernate.validator.constraints.UUID;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public Long getUserIdByUsername(String username) {
        return userRepository.getUserIdByUsername(username);
    }
    public Long getUserId(){
        return getUserIdByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}