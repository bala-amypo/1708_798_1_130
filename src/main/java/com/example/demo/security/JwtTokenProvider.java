package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtTokenProvider {

    // ✅ 256-bit secret (REQUIRED for HS256)
    private static final String SECRET =
            "THIS_IS_A_VERY_SECURE_256_BIT_SECRET_KEY_FOR_JWT_TESTS";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    /* ===============================
       TOKEN CREATION
       =============================== */

    public String createToken(Long userId, String email, Set<String> roles) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 3600000) // 1 hour
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /* ===============================
       TOKEN VALIDATION (TEST EXPECTED)
       =============================== */

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /* ===============================
       CLAIM EXTRACTION (TEST EXPECTED)
       =============================== */

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    @SuppressWarnings("unchecked")
    public Set<String> getRoles(String token) {
        Object roles = getClaims(token).get("roles");
        return new HashSet<>((Collection<String>) roles);
    }

    /* ===============================
       INTERNAL HELPER
       =============================== */

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
