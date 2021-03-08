package com.project.poker.commonlib.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BackendErrorResponse {

    public static BackendErrorResponse anErrorResponse(BackendError scaError) {
        return BackendErrorResponse.builder()
                .errorCode(scaError.name())
                .errorMessage(scaError.getErrorMessage())
                .build();
    }

    public static BackendErrorResponse anErrorResponse(String code, String message) {
        return BackendErrorResponse.builder()
                .errorCode(code)
                .errorMessage(message)
                .build();
    }

    private String errorCode;
    private String errorMessage;
}
