package com.lincz.pokedex.service;

import com.lincz.pokedex.domain.Pokemon;
import com.lincz.pokedex.domain.PokemonType;
import com.lincz.pokedex.exception.DataAlreadyExistsException;
import com.lincz.pokedex.exception.NotFoundException;
import com.lincz.pokedex.repository.PokemonRepository;
import com.lincz.pokedex.web.mappers.PokemonMapper;
import com.lincz.pokedex.web.model.PokemonDto;
import com.lincz.pokedex.web.model.PokemonPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonServiceImp implements PokemonService {

    private final PokemonRepository pokemonRepository;
    private final PokemonMapper pokemonMapper;

    @Override
    public PokemonPagedList listPokemons(String pokemonName, PokemonType pokemonType, PageRequest pageRequest) {
        log.debug("listPokemons Called");
        PokemonPagedList pokemonPagedList;
        Page<Pokemon> pokemonPage;

        if(StringUtils.hasText(pokemonName) && !ObjectUtils.isEmpty(pokemonType)) {
            pokemonPage = pokemonRepository.findAllByNameAndType(pokemonName, pokemonType, pageRequest);
        } else if(StringUtils.hasText(pokemonName) && ObjectUtils.isEmpty(pokemonType)) {
            pokemonPage = pokemonRepository.findByName(pokemonName, pageRequest);
        } else if(!StringUtils.hasText(pokemonName) && !ObjectUtils.isEmpty(pokemonType)) {
            pokemonPage = pokemonRepository.findAllByType(pokemonType, pageRequest);
        } else {
            pokemonPage = pokemonRepository.findAll(pageRequest);
        }

        pokemonPagedList = new PokemonPagedList(pokemonPage.getContent()
                .stream()
                .map(pokemonMapper::pokemonToPokemonDto)
                .collect(Collectors.toList()),
                PageRequest.of(pokemonPage.getPageable().getPageNumber(), pokemonPage.getPageable().getPageSize()),
                pokemonPage.getTotalElements());

        return pokemonPagedList;
    }

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

    @Override
    public PokemonDto findPokemonByName(String pokemonName) {
        Optional<Pokemon> pokemon = pokemonRepository.findByName(pokemonName);

        if(!pokemon.isEmpty()) {
            log.info("Pokemon found: {}", pokemon);
            return pokemonMapper.pokemonToPokemonDto(pokemon.get());
        } else {
            log.error("Pokemon not found: {}", pokemonName);
            throw new NotFoundException("Pokemon not found: " + pokemonName);
        }
    }

    @Override
    public PokemonDto findPokemonById(UUID id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);

        if(!pokemon.isEmpty()) {
            log.info("Pokemon found: {}", pokemon);
            return pokemonMapper.pokemonToPokemonDto(pokemon.get());
        } else {
            log.error("Pokemon not found: {}", id);
            throw new NotFoundException("Pokemon not found: " + id);
        }
    }

    @Override
    public PokemonDto updatePokemon(UUID id, PokemonDto pokemonDto) {

        Pokemon pokemonToUpdate = pokemonRepository.findById(id).orElseThrow(NotFoundException::new);

        pokemonToUpdate.setName(pokemonDto.getName());
        pokemonToUpdate.setType(pokemonDto.getType());
        pokemonToUpdate.setWeight(pokemonDto.getWeight());
        pokemonToUpdate.setHeight(pokemonDto.getHeight());

        return pokemonMapper.pokemonToPokemonDto(pokemonRepository.save(pokemonToUpdate));
    }

    @Override
    public void deletePokemon(UUID id) {
        pokemonRepository.deleteById(id);
    }
}
