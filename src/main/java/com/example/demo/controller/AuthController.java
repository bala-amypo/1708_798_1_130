package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserRepository userRepo,
                          PasswordEncoder encoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {

        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = User.builder()
                .email(req.getEmail())
                .name(req.getName())
                .password(encoder.encode(req.getPassword()))
                .roles(req.getRoles())
                .build();

        userRepo.save(user);

        String token = jwtTokenProvider.createToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {

        Optional<User> opt = userRepo.findByEmail(req.getEmail());

        if (opt.isEmpty() ||
            !encoder.matches(req.getPassword(), opt.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = opt.get();

        String token = jwtTokenProvider.createToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
