package com.lincz.pokedex.controller;

import com.lincz.pokedex.controller.model.LoginRequest;
import com.lincz.pokedex.controller.model.LoginResponse;
import com.lincz.pokedex.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getUsername(), request.getPassword());
    }

    @GetMapping("/logout")
    public String logout() {
        return "";
    }
}
