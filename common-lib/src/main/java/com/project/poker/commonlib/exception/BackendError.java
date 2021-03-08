package com.project.poker.commonlib.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BackendError {

    DEFAULT(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Pass error");

    private HttpStatus status;
    private String errorMessage;
}
