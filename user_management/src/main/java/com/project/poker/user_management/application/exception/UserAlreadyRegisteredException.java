package com.project.poker.user_management.application.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }

    public UserAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }
}
