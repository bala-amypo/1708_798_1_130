package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
public class JwtTokenProvider {

    // ✅ MUST BE BASE64
    private static final String SECRET =
            Base64.getEncoder().encodeToString("test-secret-key-1234567890".getBytes());

    private static final long EXPIRY = 3600000;

    private Key getKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    }

    // ===== create =====
    public String createToken(String email, Set<String> roles, Long userId) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", new ArrayList<>(roles));
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ===== validate =====
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public Set<String> getRoles(String token) {
        return new HashSet<>(
                (List<String>) getClaims(token).get("roles")
        );
    }

    public Long getUserId(String token) {
        return ((Number) getClaims(token).get("userId")).longValue();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
