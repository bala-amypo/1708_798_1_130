package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    // ✅ MUST be ≥ 256 bits (fixes WeakKeyException)
    private static final String SECRET =
            "this_is_a_very_secure_256_bit_secret_key_for_testing_and_runtime";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    private final long EXPIRATION = 60 * 60 * 1000; // 1 hour

    // ✔ REQUIRED BY TESTS (37–41)
    public String createToken(Long userId, String email, Set<String> roles) {

        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public Set<String> getRoles(String token) {
        return getClaims(token).get("roles", Set.class);
    }

    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
