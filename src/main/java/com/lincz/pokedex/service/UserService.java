package com.lincz.pokedex.service;

import com.lincz.pokedex.domain.User;
import com.lincz.pokedex.web.model.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    UserDto createUser(UserDto userDto);
}
