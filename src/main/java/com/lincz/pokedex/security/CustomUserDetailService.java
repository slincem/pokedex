package com.lincz.pokedex.security;

import com.lincz.pokedex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByUsername(username).orElseThrow();
        return UserPrincipal.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(List.of(new SimpleGrantedAuthority(user.getRole().name())))
                .status(user.getStatus())
                .build();
    }
}
