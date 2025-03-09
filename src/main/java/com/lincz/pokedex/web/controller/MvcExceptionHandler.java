package com.lincz.pokedex.web.controller;

import com.lincz.pokedex.exception.DataAlreadyExistsException;
import com.lincz.pokedex.exception.NotFoundException;
import com.lincz.pokedex.web.controller.model.CustomErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> validationErrorHandler(ConstraintViolationException e) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();

        e.getConstraintViolations().forEach(constraintViolation -> {
            errorResponse.addError(HttpStatus.BAD_REQUEST.value(), constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> validationErrorHandler(MethodArgumentNotValidException e) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();

        e.getBindingResult().getAllErrors().forEach(constraintViolation -> {
            errorResponse.addError(HttpStatus.BAD_REQUEST.value(), constraintViolation.getDefaultMessage());
        });

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<CustomErrorResponse> emailAlreadyExistsErrorHandler(DataAlreadyExistsException e) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();

        e.getErrors().forEach(error -> {
            errorResponse.addError(HttpStatus.BAD_REQUEST.value(), error);
        });

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
