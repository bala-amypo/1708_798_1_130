package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest r) {
        return service.registerUser(r);
    }

    @PostMapping("/login")
    public AuthResponse login(package com.example.demo.dto;

public class AuthResponse {

    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
 LoginRequest r) {
        return service.loginUser(r);
    }
}
