package com.lincz.pokedex.web.controller.v1;

import com.lincz.pokedex.web.model.PokemonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pokemons")
public interface PokemonController {

    ResponseEntity<PokemonDto> createPokemon(PokemonDto pokemonDto);
}
