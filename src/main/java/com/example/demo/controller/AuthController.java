package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            log.info("Registration attempt for email: {}", request.getEmail());
            
            // Validate request
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("{\"error\": \"Email is required\"}");
            }
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("{\"error\": \"Password is required\"}");
            }
            
            // Check if email already exists
            if (userRepository.existsByEmail(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("{\"error\": \"Email already exists\"}");
            }
            
            // Create new user
            User user = new User();
            user.setName(request.getName() != null ? request.getName() : "");
            user.setEmail(request.getEmail().trim().toLowerCase());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            
            // Set roles (default to USER if none provided)
            if (request.getRoles() != null && !request.getRoles().isEmpty()) {
                user.setRoles(request.getRoles());
            } else {
                user.setRoles(java.util.Set.of("USER"));
            }
            
            // Save user
            log.info("Saving user: {}", user.getEmail());
            User savedUser = userRepository.save(user);
            log.info("User saved with ID: {}", savedUser.getId());
            
            // Generate token
            String token = jwtTokenProvider.createToken(
                    savedUser.getId(),
                    savedUser.getEmail(),
                    savedUser.getRoles()
            );
            
            // Return response
            AuthResponse response = AuthResponse.builder()
                    .token(token)
                    .email(savedUser.getEmail())
                    .build();
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("Registration error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Registration failed: " + e.getMessage() + "\"}");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            log.info("Login attempt for email: {}", request.getEmail());
            
            // Validate request
            if (request.getEmail() == null || request.getEmail().trim().isEmpty() ||
                request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("{\"error\": \"Email and password are required\"}");
            }
            
            // Find user by email
            User user = userRepository.findByEmail(request.getEmail().trim().toLowerCase())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            // Check password
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("{\"error\": \"Invalid credentials\"}");
            }
            
            // Generate token
            String token = jwtTokenProvider.createToken(
                    user.getId(),
                    user.getEmail(),
                    user.getRoles()
            );
            
            // Return response
            AuthResponse response = AuthResponse.builder()
                    .token(token)
                    .email(user.getEmail())
                    .build();
            
            log.info("Login successful for: {}", user.getEmail());
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            log.warn("Login failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Invalid credentials\"}");
        } catch (Exception e) {
            log.error("Login error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Login failed: " + e.getMessage() + "\"}");
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("{\"status\": \"OK\", \"message\": \"Auth endpoint is working!\"}");
    }
}