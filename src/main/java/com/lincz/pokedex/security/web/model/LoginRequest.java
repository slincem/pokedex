package com.lincz.pokedex.security.web.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    private String username;
    private String password;
}
