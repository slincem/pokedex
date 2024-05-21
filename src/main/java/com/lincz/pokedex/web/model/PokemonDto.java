package com.lincz.pokedex.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lincz.pokedex.domain.PokemonType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
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

    @NotBlank(message = "type cannot be blank")
    private Set<PokemonType> type;

    @NotBlank(message = "weight cannot be blank")
    private Double weight;

    @NotBlank(message = "height cannot be blank")
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
