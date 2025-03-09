package com.lincz.pokedex.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lincz.pokedex.domain.User;
import com.lincz.pokedex.security.jtwmanagement.JwtProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtService {

    private final JwtProperties properties;

    public String generateToken(String userId, String username, List<String> roles) {
        return buildToken(userId, username, roles, properties.getExpirationTime());
    }

    public String generateRefreshToken(String userId, String username, List<String> roles) {
        return buildToken(userId, username, roles, properties.getRefreshExpirationTime());
    }

    public String buildToken(String userId, String username, List<String> roles, long expirationTime) {
        return JWT.create()
                .withSubject(userId)
                .withClaim("username", username)
                .withClaim("roles", roles)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(Instant.now().plus(Duration.of(expirationTime, ChronoUnit.SECONDS)))
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }

    public Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return extractTokenFromHeader(header);
    }

    public Optional<String> extractTokenFromHeader(String header) {
        if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return Optional.of(header.substring(7));
        }
        return Optional.empty();
    }

    public String extractUsernameFromToken(String refreshToken) {
        return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
                .build()
                .verify(refreshToken)
                .getClaim("username")
                .asString();
    }

    public boolean isTokenValid(String refreshToken, User user) {
        var username = extractUsernameFromToken(refreshToken);
        return username.equals(user.getUsername()) && !isTokenExpired(refreshToken);
    }

    private boolean isTokenExpired(String refreshToken) {
        return extractExpiration(refreshToken).before(new Date());
    }

    private Date extractExpiration(String refreshToken) {
        return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
                .build()
                .verify(refreshToken)
                .getExpiresAt();
    }
}
