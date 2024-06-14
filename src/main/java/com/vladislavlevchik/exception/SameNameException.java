package com.vladislavlevchik.exception;

public class SameNameException extends RuntimeException {
    public SameNameException(String message) {
        super(message);
    }
}
