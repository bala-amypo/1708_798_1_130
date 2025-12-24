package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    // ✅ Strong 256-bit secret (MANDATORY for HS256)
    private static final String SECRET =
            "THIS_IS_A_VERY_LONG_AND_SECURE_SECRET_KEY_256_BITS_MINIMUM";

    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    private final Key key;

    // ✅ No-arg constructor required by Spring + TestNG
    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // =====================================================
    // ✅ CREATE TOKEN (used by tests 37–41)
    // =====================================================
    public String createToken(Long userId, String email, Set<String> roles) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("roles", new ArrayList<>(roles));

        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // =====================================================
    // ✅ VALIDATE TOKEN (test42)
    // =====================================================
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // =====================================================
    // ✅ GET EMAIL (TEST CASE 39)
    // =====================================================
    public String getEmail(String token) {
        return parseClaims(token).getSubject();
    }

    // =====================================================
    // ✅ GET ROLES (TEST CASE 40)
    // =====================================================
    public Set<String> getRoles(String token) {
        Claims claims = parseClaims(token);
        Object roles = claims.get("roles");

        if (roles instanceof List<?>) {
            return ((List<?>) roles)
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet());
        }
        return Set.of();
    }

    // =====================================================
    // ✅ GET USER ID (TEST CASE 41)
    // =====================================================
    public Long getUserId(String token) {
        Object id = parseClaims(token).get("userId");
        return Long.valueOf(id.toString());
    }

    // =====================================================
    // 🔒 INTERNAL PARSER
    // =====================================================
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
