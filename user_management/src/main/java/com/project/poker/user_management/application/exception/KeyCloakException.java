package com.project.poker.user_management.application.exception;

public class KeyCloakException extends RuntimeException {
    public KeyCloakException(String message) {
        super(message);
    }

    public KeyCloakException(Throwable cause) {
        super(cause);
    }
}
