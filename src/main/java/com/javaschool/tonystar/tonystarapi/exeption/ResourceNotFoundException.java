package com.javaschool.tonystar.tonystarapi.exeption;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}