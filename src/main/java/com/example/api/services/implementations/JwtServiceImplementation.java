package com.example.api.services.implementations;

import com.example.api.entities.User;
import com.example.api.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImplementation implements JwtService {

    private final String secret;
    private final long expiration;

    public JwtServiceImplementation(@Value("${security.jwt.secret}") String secret,
                                    @Value("${security.jwt.expiration}") long expiration) {
        this.secret = secret;
        this.expiration = expiration;

        // Optional: Validate the secret and expiration
        if (secret == null || secret.isEmpty()) {
            throw new IllegalArgumentException("JWT secret must not be null or empty");
        }
        if (expiration <= 0) {
            throw new IllegalArgumentException("JWT expiration must be greater than 0");
        }
    }

    @Override
    public String ExtractUserId(String token) {
        return ExtractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, User user) {
        return buildToken(extraClaims, user, expiration);
    }

    public String generateToken(User user) {
        return buildToken(user, expiration);
    }

    @Override
    public <T> T ExtractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Date ExtractExpiration(String token) {
        return extractExpiration(token);
    }

    @Override
    public boolean IsTokenValid(String token, User userDetails) {
        final String userId = ExtractUserId(token);
        boolean isTokenExpired = isTokenExpired(token);
        String userid = userDetails.getId().toString();
        boolean isUserIdEqual = userId.equals(userid);
        return (isUserIdEqual && !isTokenExpired);
    }

    private Date extractExpiration(String token) {
        return ExtractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String buildToken(Map<String, Object> extraClaims, User userDetails, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private String buildToken(User userDetails, long expiration) {
        return Jwts
                .builder()
                .setSubject(userDetails.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
