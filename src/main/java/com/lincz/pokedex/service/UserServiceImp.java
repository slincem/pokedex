package com.lincz.pokedex.service;

import com.lincz.pokedex.domain.User;
import com.lincz.pokedex.domain.UserRole;
import com.lincz.pokedex.domain.UserStatus;
import com.lincz.pokedex.exception.DataAlreadyExistsException;
import com.lincz.pokedex.exception.NotFoundException;
import com.lincz.pokedex.repository.UserRepository;
import com.lincz.pokedex.web.mappers.UserMapper;
import com.lincz.pokedex.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) throws NotFoundException {

        Optional<User> opUser = userRepository.findByUsername(username);

        if(opUser.isPresent()) {
            log.info("User found to log in: {}", opUser);
            return opUser;
        } else {
            log.info("User not found when logging in: {}", username);
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        List<String> errors = new ArrayList<>();
        if (isEmailRegistered(userDto.getEmail())) {
            log.error("Email already registered: {}", userDto.getEmail());
            errors.add("There is an account with that email address: " + userDto.getEmail());
        }

        if (isUsernameRegistered(userDto.getUsername())) {
            log.error("Username already registered: {}", userDto.getUsername());
            errors.add("There is an account with that username: " + userDto.getUsername());
        }

        if(!errors.isEmpty()) {
            throw new DataAlreadyExistsException(errors);
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userMapper.userDtoToUser(userDto);
        log.info("Creating user: {}", user);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    private boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean isUsernameRegistered(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

}
