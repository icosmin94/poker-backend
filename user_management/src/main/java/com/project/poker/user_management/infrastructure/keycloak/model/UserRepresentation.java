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
public class UserRepresentation implements CustomJsonSerializable {

    private String id;
    private String email;
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String username;
}
