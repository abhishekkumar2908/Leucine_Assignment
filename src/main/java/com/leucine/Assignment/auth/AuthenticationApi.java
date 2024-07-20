package com.leucine.Assignment.auth;


import com.leucine.Assignment.security.JwtRequest;
import com.leucine.Assignment.security.JwtResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public interface AuthenticationApi {

    @PostMapping("/login")
    JwtResponse login(@RequestBody JwtRequest authenticationRequest) throws Exception;
}
