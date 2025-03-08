package com.lincz.pokedex.security.jtwmanagement;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lincz.pokedex.security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JwtToPrincipalConverter {

    public UserPrincipal convert(DecodedJWT jwt) {
        return UserPrincipal.builder()
                .userId(UUID.fromString(jwt.getSubject()))
                .username(jwt.getClaim("username").asString())
                .roles(extractRolesFromClaim(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractRolesFromClaim(DecodedJWT jwt) {
        var claim = jwt.getClaim("roles");
        if(claim.isNull() || claim.isMissing()) {
            return List.of();
        }
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
