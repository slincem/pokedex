package com.lincz.pokedex.web.controller.v1;

import com.lincz.pokedex.service.UserService;
import com.lincz.pokedex.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserControllerImp implements UserController {

    private final UserService userService;

    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
}
