package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;

import java.util.NoSuchElementException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwt;

    public UserServiceImpl(
            UserRepository repo,
            PasswordEncoder encoder,
            JwtTokenProvider jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public void registerUser(RegisterRequest req) {
        if (repo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRoles(req.getRoles());
        repo.save(u);
    }

    public AuthResponse loginUser(LoginRequest req) {
        User u = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!encoder.matches(req.getPassword(), u.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwt.createToken(u.getId(), u.getEmail(), u.getRoles());
        return new AuthResponse(token);
    }
}
