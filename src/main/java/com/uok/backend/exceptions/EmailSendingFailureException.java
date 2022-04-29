package com.uok.backend.exceptions;

public class EmailSendingFailureException extends Exception {

    public EmailSendingFailureException(String message) {
        super(message);
    }
}
