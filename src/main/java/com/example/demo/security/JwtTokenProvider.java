@Component
public class JwtTokenProvider {

    private final String secret = "secret-key";

    public String createToken(Long userId, String email, Set<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}
