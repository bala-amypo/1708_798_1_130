package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    // ✅ 256+ bit secret (SAFE)
    private static final String SECRET =
            "THIS_IS_A_VERY_LONG_AND_SECURE_SECRET_KEY_256_BITS_MINIMUM";

    private static final long EXPIRATION_MS = 60 * 60 * 1000; // 1 hour

    private final SecretKey key;

    public JwtTokenProvider() {
        // ✅ NEVER fails
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ----------------------------------------------------
    // CREATE TOKEN (used by tests 37–41)
    // ----------------------------------------------------
    public String createToken(Long userId, String email, Set<String> roles) {

        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("roles", roles);

        return Jwts.builder()
                .setSubject(email)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ----------------------------------------------------
    // VALIDATE TOKEN (tests 38 & 42)
    // ----------------------------------------------------
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

    // ----------------------------------------------------
    // EXTRACT EMAIL (test 39)
    // ----------------------------------------------------
    public String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ----------------------------------------------------
    // EXTRACT ROLES (test 40)
    // ----------------------------------------------------
    @SuppressWarnings("unchecked")
    public Set<String> getRoles(String token) {
        return (Set<String>) Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
    }

    // ----------------------------------------------------
    // EXTRACT USER ID (test 41)
    // ----------------------------------------------------
    public Long getUserId(String token) {
        return Long.valueOf(
                Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .get("userId").toString()
        );
    }
}
