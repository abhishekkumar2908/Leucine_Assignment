package com.leucine.Assignment.service;

import com.leucine.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUserIdByUsername(authentication.getName());
    }
}