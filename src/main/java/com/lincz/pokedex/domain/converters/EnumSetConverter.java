package com.lincz.pokedex.domain.converters;

import com.lincz.pokedex.domain.PokemonType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class EnumSetConverter implements AttributeConverter<EnumSet<PokemonType>, String> {

    @Override
    public String convertToDatabaseColumn(EnumSet<PokemonType> attribute) {
        return attribute == null ? "" : attribute.stream().map(Enum::name).collect(Collectors.joining(","));
    }

    @Override
    public EnumSet<PokemonType> convertToEntityAttribute(String dbData) {
        return dbData == null || dbData.isEmpty()
                ? EnumSet.noneOf(PokemonType.class)
                : Arrays.stream(dbData.split(","))
                .map(PokemonType::valueOf)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(PokemonType.class)));
    }
}

