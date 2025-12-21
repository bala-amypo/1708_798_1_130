package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String SECRET = "secretkey";

    public String createToken(Long id, String email, Set<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("id", id)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        return true;
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    public Set<String> getRoles(String token) {
        return new HashSet<>(
                getClaims(token).get("roles", List.class));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token).getBody();
    }
}
