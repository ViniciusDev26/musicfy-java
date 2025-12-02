package com.github.viniciusdev26.musicfy.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String entityName, Object key) {
        super(String.format("%s with key '%s' was not found", entityName, key));
    }
}
