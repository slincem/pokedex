package com.lincz.pokedex.security.service;

import com.lincz.pokedex.domain.User;
import com.lincz.pokedex.exception.NotFoundException;
import com.lincz.pokedex.repository.UserRepository;
import com.lincz.pokedex.security.model.Token;
import com.lincz.pokedex.security.repository.TokenRepository;
import com.lincz.pokedex.security.web.model.LoginResponse;
import com.lincz.pokedex.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public LoginResponse attemptLogin(String username, String password) {
        var authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found when logging in"));

        var token = jwtService.generateToken(user.getId().toString(), user.getUsername(), List.of(user.getRole().name()));
        var refreshToken = jwtService
                .generateRefreshToken(user.getId().toString(), user.getUsername(), List.of(user.getRole().name()));
        revokeAllUserTokens(user);
        saveUserToken(user, token);

        return LoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(final User user) {
        final List<Token> validUserTokens = tokenRepository.findAllValidIsFalseOrREvokedIsFalseByUserId(user.getId());

        if(!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
        }
        tokenRepository.saveAll(validUserTokens);
    }

    public LoginResponse refreshToken(String authHeader) {
        var refreshToken = jwtService.extractTokenFromHeader(authHeader)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Bearer token"));
        final String username = jwtService.extractUsernameFromToken(refreshToken);
        if(username == null) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }

        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found when refreshing token"));
        if(!jwtService.isTokenValid(refreshToken, user)) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }

        var accessToken = jwtService.generateToken(user.getId().toString(), user.getUsername(), List.of(user.getRole().name()));
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        }
}
