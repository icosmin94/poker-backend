package com.project.poker.user_management.infrastructure.keycloak.model;

import com.project.poker.user_management.infrastructure.util.CustomJsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCommand implements CustomJsonSerializable {
    private String type;
    private boolean temporary;
    private String value;

    public static UpdateUserCommand anUpdatePasswordCommand(String password) {
        return UpdateUserCommand.builder()
                .type("password")
                .temporary(false)
                .value(password)
                .build();
    }
}
