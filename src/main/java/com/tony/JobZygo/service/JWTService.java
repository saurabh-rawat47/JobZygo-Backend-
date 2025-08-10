package com.tony.JobZygo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {

  // Read secret from env/property; fall back to a deterministic dev secret
  @Value("${jwt.secret:}")
  private String configuredSecret;

  private static final String DEFAULT_DEV_SECRET =
      "change-me-dev-secret-32+chars-long-change-me-123456";

  private SecretKey signingKey;

  @PostConstruct
  public void init() {
    String secret = configuredSecret;
    if (secret == null || secret.trim().isEmpty()) {
      // Allow overriding via environment variable if not set in properties
      String envSecret = System.getenv("JWT_SECRET");
      if (envSecret != null && !envSecret.trim().isEmpty()) {
        secret = envSecret;
      } else {
        // Last resort: deterministic dev secret (OK for local dev only)
        secret = DEFAULT_DEV_SECRET;
      }
    }
    this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(@NonNull String username) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
        .claims()
        .add(claims)
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + (1000L * 60 * 60))) // 1 hour
        .and()
        .signWith(signingKey)
        .compact();
  }

  public String extractUserName(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(signingKey)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String userName = extractUserName(token);
    return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
}
