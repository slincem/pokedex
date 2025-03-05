package com.lincz.pokedex.service;

import com.lincz.pokedex.domain.PokemonType;
import com.lincz.pokedex.web.model.PokemonDto;
import com.lincz.pokedex.web.model.PokemonPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface PokemonService {

    PokemonPagedList listPokemons(String pokemonName, PokemonType pokemonType, PageRequest of);

    PokemonDto createPokemon(PokemonDto pokemonDto);

    PokemonDto findPokemonByName(String pokemonName);

    PokemonDto findPokemonById(UUID id);

    PokemonDto updatePokemon(UUID id, PokemonDto pokemonDto);

    void deletePokemon(UUID id);
}
