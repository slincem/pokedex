package com.lincz.pokedex.web.controller.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CustomErrorViolation {
    private final String message;
}
