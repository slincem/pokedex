package com.lincz.pokedex.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PokemonPagedList extends PageImpl<PokemonDto> implements Serializable {

    static final long serialVersionUID = 3899328486949435418L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PokemonPagedList(@JsonProperty("content") List<PokemonDto> content, @JsonProperty("number") int number,
                         @JsonProperty("size") int size, @JsonProperty ("totalElements") Long totalElements,
                         @JsonProperty("pageable") JsonNode pageable, @JsonProperty ("last") boolean last,
                         @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort,
                         @JsonProperty("first") boolean first, @JsonProperty("numberofElements") int numberofElements) {
        super (content, PageRequest.of(number, size), totalElements) ;
    }

    public PokemonPagedList(List<PokemonDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PokemonPagedList(List<PokemonDto> content) {
        super(content);
    }
}
