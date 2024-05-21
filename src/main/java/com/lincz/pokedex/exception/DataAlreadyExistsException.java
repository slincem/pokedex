package com.lincz.pokedex.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class DataAlreadyExistsException extends RuntimeException {

    private final List<String> errors;

}
