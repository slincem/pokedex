package com.lincz.pokedex.web.controller;

import com.lincz.pokedex.web.controller.model.LoginRequest;
import com.lincz.pokedex.web.controller.model.LoginResponse;
import com.lincz.pokedex.service.AuthService;
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

    @GetMapping("/logout")
    @PermitAll
    public String logout() {
        return "";
    }
}
