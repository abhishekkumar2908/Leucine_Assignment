package com.leucine.Assignment.auth;


import com.leucine.Assignment.security.JwtRequest;
import com.leucine.Assignment.security.JwtResponse;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/auth")
public interface LoginController {

    @PostMapping("/login")
    JwtResponse login(@RequestBody JwtRequest authenticationRequest) throws Exception;
}
