package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    // TEST CONSTRUCTOR
    public AuthController(UserRepository repo,
                          PasswordEncoder encoder,
                          JwtTokenProvider jwt) {
        this.service = new UserServiceImpl(repo, encoder, jwt);
    }

    // SPRING CONSTRUCTOR
    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(
                service.loginUser(
                        new LoginRequest(request.getEmail(), request.getPassword())
                )
        );
    }
}
