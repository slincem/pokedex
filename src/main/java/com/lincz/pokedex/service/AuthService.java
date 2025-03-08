package com.lincz.pokedex.service;

import com.lincz.pokedex.web.controller.model.LoginResponse;
import com.lincz.pokedex.security.jtwmanagement.JwtIssuer;
import com.lincz.pokedex.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(String username, String password) {
        var authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();
        var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        var token = jwtIssuer.issue(principal.getUserId().toString(), principal.getUsername(), roles);
        var refreshToken = UUID.randomUUID().toString();
        return LoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

}
