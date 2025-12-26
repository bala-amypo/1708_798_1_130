package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtTokenProvider {

    private static final String SECRET =
            "mySuperSecretKeyThatIsAtLeast32CharactersLong123";

    private static final long EXPIRATION = 86400000; // 1 day

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Long userId, String email, List<String> roles) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String email) {
        try {
            return getEmailFromToken(token).equals(email)
                    && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    public List<String> getRolesFromToken(String token) {
        return parseClaims(token).get("roles", List.class);
    }

    public Long getUserIdFromToken(String token) {
        Object id = parseClaims(token).get("userId");
        return id instanceof Integer ? ((Integer) id).longValue() : (Long) id;
    }

    private boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
