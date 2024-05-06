package com.lincz.pokedex.web.controller.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CustomErrorResponse {

    @JsonAlias("status")
    private Map<Integer, List<CustomErrorViolation>> errorMessages;

    public CustomErrorResponse() {
        this.errorMessages = new HashMap<>();
    }

    public void addError(int status, String errorMessage) {
        List<CustomErrorViolation> errors = errorMessages.getOrDefault(status, new ArrayList<>());
        errors.add(CustomErrorViolation.builder()
                .message(errorMessage)
                .build());
        errorMessages.put(status, errors);
    }

}
