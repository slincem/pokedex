package com.lincz.pokedex.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Exception generatedException) {
        super(message, generatedException);
    }

}
