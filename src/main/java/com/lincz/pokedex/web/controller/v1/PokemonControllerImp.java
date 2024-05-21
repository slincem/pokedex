package com.lincz.pokedex.web.controller.v1;

import com.lincz.pokedex.service.PokemonService;
import com.lincz.pokedex.web.model.PokemonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PokemonControllerImp implements PokemonController {

    private final PokemonService pokemonService;

    public ResponseEntity<PokemonDto> createPokemon(PokemonDto pokemonDto) {
        return new ResponseEntity<>(pokemonDto, HttpStatus.CREATED);
    }
}
