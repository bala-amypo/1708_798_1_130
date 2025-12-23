package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.Base64;

@Component
public class JwtTokenProvider {

    // ✅ VALID Base64 secret (fixes Illegal base64 character error)
    private static final String SECRET_KEY =
            Base64.getEncoder().encodeToString(
                    "very-secret-key-for-testing-only".getBytes()
            );

    private static final long EXPIRATION_MS = 3600000; // 1 hour

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
    }

    // =====================================================
    // TOKEN CREATION
    // =====================================================
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", Set.of("USER"));
        claims.put("userId", 1L);

        return Jwts.builder()
                .setSubject(email)
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // =====================================================
    // TOKEN VALIDATION
    // =====================================================
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


    public String getEmailFromToken(String token) {
        return getAllClaims(token).getSubject();
    }

    public Set<String> getRolesFromToken(String token) {
        Object roles = getAllClaims(token).get("roles");
        return roles == null ? Set.of() : new HashSet<>((Collection<String>) roles);
    }

    public Long getUserIdFromToken(String token) {
        Object id = getAllClaims(token).get("userId");
        return id == null ? null : Long.valueOf(id.toString());
    }


    public String getEmail(String token) {
        return getEmailFromToken(token);
    }

    public Set<String> getRoles(String token) {
        return getRolesFromToken(token);
    }

    
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
