package com.nanda.controller;

import com.nanda.config.JwtUtil;
import com.nanda.model.AuthRequest;
import com.nanda.model.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
        JwtUtil jwtUtil,
        UserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder
    ) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        UserDetails user =userDetailsService.loadUserByUsername(req.getUsername());
        if (!passwordEncoder.matches(req.getPassword(),
        user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
