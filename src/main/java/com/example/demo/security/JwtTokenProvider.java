package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class JwtTokenProvider {

    private final String SECRET = "secretsecretsecretsecretsecret";

    public String createToken(Long userId, String email, Set<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    public boolean validateToken(String token) {
        return true;
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Set<String> getRoles(String token) {
        return Set.of("USER");
    }
}
