package com.lincz.pokedex.web.controller.v1;

import com.lincz.pokedex.domain.PokemonType;
import com.lincz.pokedex.service.PokemonService;
import com.lincz.pokedex.web.model.PokemonDto;
import com.lincz.pokedex.web.model.PokemonPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PokemonControllerImp implements PokemonController {

    private final PokemonService pokemonService;

    @Override
    public ResponseEntity<PokemonPagedList> listPokemons(Integer pageNumber, Integer pageSize, String pokemonName, PokemonType pokemonType) {
        PokemonPagedList pokemonList = pokemonService.listPokemons(pokemonName, pokemonType, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(pokemonList, HttpStatus.OK);
    }

    public ResponseEntity<PokemonDto> createPokemon(PokemonDto pokemonDto) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PokemonDto> findPokemonById(UUID id) {
        return new ResponseEntity<>(pokemonService.findPokemonById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PokemonDto> findPokemonByName(String name) {
        return new ResponseEntity<>(pokemonService.findPokemonByName(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PokemonDto> updatePokemon(UUID id, PokemonDto pokemonDto) {
        return new ResponseEntity<>(pokemonService.updatePokemon(id, pokemonDto), HttpStatus.OK);
    }

    @Override
    public void deletePokemon(UUID id) {
        pokemonService.deletePokemon(id);
    }
}


