package com.project.poker.commonlib.exception;

public class KeyCloakException extends RuntimeException {
    public KeyCloakException(String message) {
        super(message);
    }

    public KeyCloakException(Throwable cause) {
        super(cause);
    }
}
