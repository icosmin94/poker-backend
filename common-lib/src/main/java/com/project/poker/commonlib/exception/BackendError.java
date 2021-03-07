package com.project.poker.commonlib.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackendError {
    private String errorMessage;
    private HttpStatus status;
}
