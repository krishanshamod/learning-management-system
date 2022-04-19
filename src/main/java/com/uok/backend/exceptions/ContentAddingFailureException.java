package com.uok.backend.exceptions;

public class ContentAddingFailureException extends Exception {

    public ContentAddingFailureException() {
        super("Content adding failure");
    }

    public ContentAddingFailureException(String message) {
        super(message);
    }
}
