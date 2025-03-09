package com.lincz.pokedex.security.web;

import com.lincz.pokedex.security.service.AuthService;
import com.lincz.pokedex.security.web.model.LoginRequest;
import com.lincz.pokedex.security.web.model.LoginResponse;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @PermitAll
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getUsername(), request.getPassword());
    }

    @PostMapping("/refresh")
    @PermitAll
    public LoginResponse login(@RequestHeader("Authorization") String authHeader) {
        return authService.refreshToken(authHeader);
    }

    @GetMapping("/logout")
    @PermitAll
    public String logout() {
        return "";
    }
}
