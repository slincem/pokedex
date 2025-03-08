package com.lincz.pokedex.configuration;

import com.lincz.pokedex.domain.User;
import com.lincz.pokedex.domain.UserRole;
import com.lincz.pokedex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DefaultAdmin implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        try {
            User userAdmin = User.builder()
                    .username("admin")
                    .password("$2a$12$qcI1SulHdRvw5PtNWx8lNeEgb2UqYEEdR.Wo2rvwtezSN140NURvW")
                    .role(UserRole.ADMIN)
                    .email("admin@gmail.com")
                    .build();
            userRepository.save(userAdmin);
        } catch (DataIntegrityViolationException e) {
            log.info("Admin already exists");
        }
    }
}
