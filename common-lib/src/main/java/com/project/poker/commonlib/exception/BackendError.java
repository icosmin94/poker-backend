package com.project.poker.commonlib.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BackendError {

    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Pass error"),
    KEY_CLOAK_EXCEPTION(HttpStatus.BAD_REQUEST, "Authorization Exception"),
    USER_ALREADY_REGISTERED_EXCEPTION(HttpStatus.BAD_REQUEST, "User already registered");

    private HttpStatus status;
    private String errorMessage;
}
