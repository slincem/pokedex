package com.lincz.pokedex.service;

import com.lincz.pokedex.domain.User;
import com.lincz.pokedex.exception.NotFoundException;
import com.lincz.pokedex.web.model.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username) throws NotFoundException;

    UserDto createUser(UserDto userDto);
}
