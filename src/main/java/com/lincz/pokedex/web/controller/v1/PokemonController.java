package com.lincz.pokedex.web.controller.v1;

import com.lincz.pokedex.domain.PokemonType;
import com.lincz.pokedex.web.model.PokemonDto;
import com.lincz.pokedex.web.model.PokemonPagedList;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pokemons")
public interface PokemonController {

    @GetMapping
    @PermitAll
    ResponseEntity<PokemonPagedList> listPokemons(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize,
                                                      @RequestParam(value = "pokemonName", required = false) String pokemonName,
                                                      @RequestParam(value = "pokemonType", required = false) PokemonType pokemonType);

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<PokemonDto> createPokemon(@RequestBody @Validated PokemonDto pokemonDto);

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    ResponseEntity<PokemonDto> findPokemonById(@PathVariable UUID id);

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    ResponseEntity<PokemonDto> findPokemonByName(@PathVariable String name);

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<PokemonDto> updatePokemon(@PathVariable UUID id, @RequestBody @Validated PokemonDto pokemonDto);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    void deletePokemon(@PathVariable UUID id);
}
