package com.leucine.Assignment.controllerImpl;


import com.leucine.Assignment.enums.UserRole;
import com.leucine.Assignment.auth.LoginController;
import com.leucine.Assignment.security.CustomUserDetailsService;
import com.leucine.Assignment.security.JwtRequest;
import com.leucine.Assignment.security.JwtResponse;
import com.leucine.Assignment.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginControllerImpl implements LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public JwtResponse login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_" + UserRole.valueOf(authenticationRequest.getRole()).name()))) {
            throw new Exception("INVALID_ROLE");
        }

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