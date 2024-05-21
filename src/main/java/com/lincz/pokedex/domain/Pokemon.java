package com.lincz.pokedex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Pokemon extends BaseEntity {

    @Builder
    public Pokemon(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                   String name, EnumSet<PokemonType> type, Double weight, Double height) {
        super(id, version, createdDate, lastModifiedDate);

        this.name = name;
        this.type = type;
        this.weight = weight;
        this.height = height;
    }

    private String name;

    @Enumerated
    private EnumSet<PokemonType> type;

    private Double weight;
    private Double height;
}
