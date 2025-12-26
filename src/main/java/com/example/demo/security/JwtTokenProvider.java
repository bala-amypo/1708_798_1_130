package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    // MUST be ≥ 32 chars (256 bits)
    private static final String SECRET =
            "mySuperSecretKeyThatIsAtLeast32CharactersLong123";

    private static final long EXPIRATION_MS = 86400000; // 1 day

    private SecretKey key() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Long userId, String email, Set<String> roles) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
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

    public Set<String> getRoles(String token) {
        return getClaims(token).get("roles", Set.class);
    }

    public Long getUserId(String token) {
        Object id = getClaims(token).get("userId");
        if (id instanceof Integer) {
            return ((Integer) id).longValue();
        }
        return (Long) id;
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
