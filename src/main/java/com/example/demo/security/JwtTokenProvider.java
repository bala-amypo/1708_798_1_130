package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // ✅ VALID Base64 secret (VERY IMPORTANT)
    private static final String SECRET =
            "bXktc3VwZXItc2VjcmV0LWtleS0xMjM0NTY=";

    private final Key key;

    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    }

    // ✅ Used by AuthController + tests
    public String createToken(Long userId, String email, Set<String> roles) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("uid", userId);
        claims.put("roles", roles);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ test38, test42
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
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        Object uid = getClaims(token).get("uid");
        return uid == null ? null : Long.valueOf(uid.toString());
    }

    @SuppressWarnings("unchecked")
    public Set<String> getRoles(String token) {
        Object roles = getClaims(token).get("roles");
        if (roles instanceof Collection<?>) {
            return ((Collection<?>) roles).stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet());
        }
        return Set.of();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
