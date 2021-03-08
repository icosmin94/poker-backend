package com.project.poker.commonlib.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebInputException;

@Slf4j
public class GlobalExceptionHandler {

    public static ResponseEntity<BackendErrorResponse> handleServerWebInputException(ServerWebInputException e) {

        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(BackendError.BAD_REQUEST.getStatus())
                .body(BackendErrorResponse.anErrorResponse(BackendError.BAD_REQUEST.name(), e.getMessage()));
    }

    public static ResponseEntity<BackendErrorResponse> handleException(Exception e) {

        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(BackendError.DEFAULT.getStatus())
                .body(BackendErrorResponse.anErrorResponse(BackendError.DEFAULT));
    }

    public static ResponseEntity<BackendErrorResponse> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {

        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(BackendError.USER_ALREADY_REGISTERED_EXCEPTION.getStatus())
                .body(BackendErrorResponse.anErrorResponse(BackendError.USER_ALREADY_REGISTERED_EXCEPTION));
    }

    public static ResponseEntity<BackendErrorResponse> handleKeyCloakException(KeyCloakException e) {

        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(BackendError.DEFAULT.getStatus())
                .body(BackendErrorResponse.anErrorResponse(BackendError.DEFAULT.name(), e.getMessage()));
    }

}
