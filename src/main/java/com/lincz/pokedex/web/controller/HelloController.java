package com.lincz.pokedex.web.controller;

import com.lincz.pokedex.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return "If you see then you're logged in as: " + userPrincipal.getUsername() + " User ID: " +
                userPrincipal.getUserId() + " Role: " + userPrincipal.getAuthorities();
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return "If you see then you're an ADMIN: " + userPrincipal.getUsername() + " User ID: " +
                userPrincipal.getUserId();
    }

}
