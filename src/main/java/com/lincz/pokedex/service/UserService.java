package com.lincz.pokedex.service;

import com.lincz.pokedex.model.User;
import com.lincz.pokedex.model.UserRole;
import com.lincz.pokedex.model.UserStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    //private final UserRepository userRepository;
    private static final String EXISTING_USERNAME = "linczz";
    private static final String EXISTING_USERNAME_ADMIN = "admin";

    public Optional<User> findByUsername(String username) {
        //return userRepository.findByUsername(username);
        if(EXISTING_USERNAME.equalsIgnoreCase(username)) {
            var user = User.builder()
                    .id(1L)
                    .username(username)
                    .password("$2a$12$qcI1SulHdRvw5PtNWx8lNeEgb2UqYEEdR.Wo2rvwtezSN140NURvW") //test
                    .role(UserRole.USER)
                    .status(UserStatus.ACTIVE)
                    .build();
            return Optional.of(user);
        } else if (EXISTING_USERNAME_ADMIN.equalsIgnoreCase(username)) {
            var user = User.builder()
                    .id(99L)
                    .username(username)
                    .password("$2a$12$qcI1SulHdRvw5PtNWx8lNeEgb2UqYEEdR.Wo2rvwtezSN140NURvW") //test
                    .status(UserStatus.ACTIVE)
                    .role(UserRole.ADMIN)
                    .build();
            return Optional.of(user);
        }

        return Optional.empty();
    }
}
