package com.lincz.pokedex.service;

import com.lincz.pokedex.domain.Pokemon;
import com.lincz.pokedex.domain.User;
import com.lincz.pokedex.exception.DataAlreadyExistsException;
import com.lincz.pokedex.repository.PokemonRepository;
import com.lincz.pokedex.web.mappers.PokemonMapper;
import com.lincz.pokedex.web.model.PokemonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonServiceImp implements PokemonService {

    private final PokemonRepository pokemonRepository;
    private final PokemonMapper pokemonMapper;

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        List<String> errors = new ArrayList<>();

        if (isPokemonRegistered(pokemonDto.getName())) {
            log.error("Pokemon already exists: {}", pokemonDto.getName());
            errors.add("This pokemon already exists: " + pokemonDto.getName());
        }

        if(!errors.isEmpty()) {
            throw new DataAlreadyExistsException(errors);
        }

        log.info("Creating pokemon: {}", pokemonDto);
        Pokemon pokemon = pokemonMapper.pokemonDtoToPokemon(pokemonDto);
        return pokemonMapper.pokemonToPokemonDto(pokemonRepository.save(pokemon));
    }

    private boolean isPokemonRegistered(String name) {
        return pokemonRepository.existsByName(name);
    }
}
