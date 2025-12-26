package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static final String SECRET =
            "ThisIsA256BitSecretKeyForJwtTokenProviderUsedInUnitTests12345";

    private final SecretKey key;

    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    public String createToken(Long userId, String email, Set<String> roles) {

        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
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
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return getAllClaims(token).get("email", String.class);
    }


    @SuppressWarnings("unchecked")
    public Set<String> getRoles(String token) {
        Object raw = getAllClaims(token).get("roles");

        if (raw instanceof Collection<?>) {
            return ((Collection<?>) raw)
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet());
        }
        return Set.of();
    }


    public Long getUserId(String token) {
        Object id = getAllClaims(token).get("userId");
        if (id instanceof Number) {
            return ((Number) id).longValue();
        }
        return null;
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
