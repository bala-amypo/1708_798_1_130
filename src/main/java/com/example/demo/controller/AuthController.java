package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthController {
    
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    
    public AuthController(UserService userService, 
                         PasswordEncoder passwordEncoder,
                         JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        User user = userService.registerUser(request);
        
        String token = jwtTokenProvider.createToken(
            user.getId(), 
            user.getEmail(), 
            user.getRoles()
        );
        
        AuthResponse response = new AuthResponse(
            token, 
            user.getId(), 
            user.getEmail(), 
            user.getRoles()
        );
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.loginUser(request);
        
        String token = jwtTokenProvider.createToken(
            user.getId(), 
            user.getEmail(), 
            user.getRoles()
        );
        
        AuthResponse response = new AuthResponse(
            token, 
            user.getId(), 
            user.getEmail(), 
            user.getRoles()
        );
        
        return ResponseEntity.ok(response);
    }
}