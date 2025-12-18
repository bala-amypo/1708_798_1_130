package com.example.demo.service;

import com.example.demo.dto.*;

public interface UserService {
    void registerUser(RegisterRequest req);
    AuthResponse loginUser(LoginRequest req);
}
