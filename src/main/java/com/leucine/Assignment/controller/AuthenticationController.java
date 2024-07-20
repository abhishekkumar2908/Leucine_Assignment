package com.leucine.Assignment.controller;


import com.leucine.Assignment.UserRole;
import com.leucine.Assignment.auth.AuthenticationApi;
import com.leucine.Assignment.security.CustomUserDetailsService;
import com.leucine.Assignment.security.JwtRequest;
import com.leucine.Assignment.security.JwtResponse;
import com.leucine.Assignment.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public JwtResponse login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtUtil.generateToken(userDetails.getUsername(), UserRole.valueOf(authenticationRequest.getRole()));

        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
