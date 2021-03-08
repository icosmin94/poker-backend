package com.project.poker.commonlib.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }

    public UserAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }
}
