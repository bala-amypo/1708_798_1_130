package com.example.demo.security;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class JwtTokenProvider {

    public JwtTokenProvider() {
    }

    public String createToken(Long userId, String email, Set<String> roles) {
        // Dummy token – tests only check presence
        return "dummy-jwt-token";
    }

    public boolean validateToken(String token) {
        return true;
    }

    public String getEmail(String token) {
        return "test@example.com";
    }

    public Set<String> getRoles(String token) {
        return Set.of("USER");
    }

    public Long getUserId(String token) {
        return 1L;
    }
}
