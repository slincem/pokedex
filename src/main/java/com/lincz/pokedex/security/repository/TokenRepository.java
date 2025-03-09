package com.lincz.pokedex.security.repository;

import com.lincz.pokedex.security.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAllValidIsFalseOrREvokedIsFalseByUserId(UUID userId);
}
