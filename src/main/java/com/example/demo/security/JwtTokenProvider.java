package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    private final String SECRET = "secret-key";
    private final long VALIDITY = 86400000; // 1 day

    public String createToken(Long userId, String email, Set<String> roles) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("roles", roles);

        Date now = new Date();
        Date exp = new Date(now.getTime() + VALIDITY);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        return true;
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Set<String> getRoles(String token) {
        return (Set<String>) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
    }

    public Long getUserId(String token) {
        return ((Number) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("userId")).longValue();
    }
}
