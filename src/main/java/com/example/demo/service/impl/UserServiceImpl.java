package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.model.User;
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

    public UserServiceImpl(UserRepository repo,
                           PasswordEncoder encoder,
                           JwtTokenProvider jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public AuthResponse registerUser(RegisterRequest r) {
        if (repo.findByEmail(r.getEmail()).isPresent())
            throw new IllegalArgumentException("Duplicate email");

        User u = new User();
        u.setName(r.getName());
        u.setEmail(r.getEmail());
        u.setPassword(encoder.encode(r.getPassword()));
        u.setRoles(r.getRoles());
        repo.save(u);

        return new AuthResponse(jwt.createToken(
                u.getId(), u.getEmail(), u.getRoles()));
    }

    public AuthResponse loginUser(LoginRequest r) {
        User u = repo.findByEmail(r.getEmail())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!encoder.matches(r.getPassword(), u.getPassword()))
            throw new IllegalArgumentException("Invalid credentials");

        return new AuthResponse(jwt.createToken(
                u.getId(), u.getEmail(), u.getRoles()));
    }
}
