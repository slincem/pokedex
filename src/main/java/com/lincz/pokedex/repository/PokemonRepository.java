package com.lincz.pokedex.repository;

import com.lincz.pokedex.domain.Pokemon;
import com.lincz.pokedex.domain.PokemonType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {
    boolean existsByName(String name);

    Optional<Pokemon> findByName(String name);

    Page<Pokemon> findByName(String name, PageRequest pageRequest);

    Page<Pokemon> findAllByNameAndType(String name, PokemonType type, PageRequest pageRequest);

    Page<Pokemon> findAllByName(String name, PageRequest pageRequest);

    Page<Pokemon> findAllByType(PokemonType type, PageRequest pageRequest);
}
