package com.example.demo.service.impl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public AuthResponse register(RegisterRequest request) {
        return new AuthResponse("REGISTER_SUCCESS", "dummy-token");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return new AuthResponse("LOGIN_SUCCESS", "dummy-token");
    }
}
