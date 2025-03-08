package com.lincz.pokedex.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lincz.pokedex.domain.PokemonType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.EnumSet;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PokemonDto implements Serializable {

    private static final long serialVersionUID = -1634180736907271330L;

    @Null
    private UUID id;

    @NotBlank(message = "Username cannot be blank")
    private String name;

    @NotNull(message = "type cannot be null")
    @Enumerated(EnumType.STRING)
    private EnumSet<PokemonType> type;

    @NotNull(message = "weight cannot be null")
    @Positive
    private Double weight;

    @NotNull(message = "height cannot be null")
    @Positive
    private Double height;

    @Null
    private Integer version;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;
}
