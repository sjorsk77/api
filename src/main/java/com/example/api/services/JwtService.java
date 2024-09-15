package com.example.api.services;

import com.example.api.entities.User;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;


public interface JwtService {
    String ExtractUserId(String token);
    String generateToken(Map<String, Object> extraClaims, User user);
    String generateToken(User user);
    <T> T ExtractClaim(String token, Function<Claims, T> claimsResolver);
    Date ExtractExpiration(String token);
    boolean IsTokenValid(String token, User userDetails);
}
