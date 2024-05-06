package com.lincz.pokedex.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class CredentialsAlreadyExistsException extends RuntimeException {

    private final List<String> errors;

}
