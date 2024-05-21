package com.lincz.pokedex.web.mappers;

import com.lincz.pokedex.domain.Pokemon;
import com.lincz.pokedex.web.model.PokemonDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface PokemonMapper {

    PokemonDto pokemonToPokemonDto(Pokemon pokemon);
    Pokemon pokemonDtoToPokemon(PokemonDto pokemonDto);
}
