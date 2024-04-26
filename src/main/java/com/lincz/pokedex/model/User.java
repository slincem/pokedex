package com.lincz.pokedex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private UserRole role;

    private UserStatus status;
}
