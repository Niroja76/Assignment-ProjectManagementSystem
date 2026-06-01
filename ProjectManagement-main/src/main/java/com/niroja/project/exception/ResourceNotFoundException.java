package com.niroja.project.exception;

public class ResourceNotFoundException 
        extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
} 