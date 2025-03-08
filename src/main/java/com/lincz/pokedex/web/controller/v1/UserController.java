package com.lincz.pokedex.web.controller.v1;

import com.lincz.pokedex.service.UserService;
import com.lincz.pokedex.web.model.UserDto;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public interface UserController {
    @PostMapping
    @PermitAll
    ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto userDto);
}
