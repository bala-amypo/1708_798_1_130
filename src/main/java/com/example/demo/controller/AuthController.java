package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwt;

    // ✅ EXACT constructor required by TestNG
    public AuthController(UserRepository userRepo,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwt) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
    }

    // ===== REGISTER =====
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {

        // ✅ DUPLICATE EMAIL → 409
        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.status(409).build();
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setName(req.getName());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRoles(req.getRoles());

        userRepo.save(user);

        String token = jwt.createToken(
                user.getEmail(),
                user.getRoles(),
                user.getId()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }

    // ===== LOGIN =====
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {

        User user = userRepo.findByEmail(req.getEmail()).orElse(null);

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build(); // ❌ NO EXCEPTION
        }

        String token = jwt.createToken(
                user.getEmail(),
                user.getRoles(),
                user.getId()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
