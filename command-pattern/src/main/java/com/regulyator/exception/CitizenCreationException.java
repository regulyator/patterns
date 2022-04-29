package com.regulyator.exception;

public class CitizenCreationException extends RuntimeException{
    public CitizenCreationException(String message) {
        super(message);
    }

    public CitizenCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
