package com.lincz.pokedex.web.controller.v1;

import com.lincz.pokedex.domain.Pokemon;
import com.lincz.pokedex.domain.PokemonType;
import com.lincz.pokedex.web.model.PokemonDto;
import com.lincz.pokedex.web.model.PokemonPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pokemons")
public interface PokemonController {

    @GetMapping
    public ResponseEntity<PokemonPagedList> listPokemons(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize,
                                                      @RequestParam(value = "pokemonName", required = false) String pokemonName,
                                                      @RequestParam(value = "pokemonType", required = false) PokemonType pokemonType);

    @PostMapping
    ResponseEntity<PokemonDto> createPokemon(@RequestBody @Validated PokemonDto pokemonDto);

    @GetMapping("/{id}")
    ResponseEntity<PokemonDto> findPokemonById(@PathVariable UUID uuid);

    @GetMapping("/{name}")
    ResponseEntity<PokemonDto> findPokemonByName(@PathVariable String name);

    @PutMapping("/{id}")
    ResponseEntity<PokemonDto> updatePokemon(@PathVariable UUID id, @RequestBody @Validated PokemonDto pokemonDto);

    @DeleteMapping("/{id}")
    void deletePokemon(@PathVariable UUID id);
}
