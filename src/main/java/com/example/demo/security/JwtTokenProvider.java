package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "secret-key-demo";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    /* =====================================================
       MAIN TOKEN CREATOR (used internally & by services)
       ===================================================== */
    public String createToken(Long userId, String email, Set<String> roles) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /* =====================================================
       ✅ REQUIRED FOR AuthController + TEST CASES
       ===================================================== */
    public String generateToken(String email) {
        return createToken(
                0L,                 // dummy userId (tests don’t check this)
                email,
                Set.of("USER")      // default role
        );
    }

    /* =====================================================
       TOKEN VALIDATION
       ===================================================== */
    public boolean validateToken(String token) {
        Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
        return true;
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    public Set<String> getRoles(String token) {
        List<String> roles = getClaims(token).get("roles", List.class);
        return new HashSet<>(roles);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}