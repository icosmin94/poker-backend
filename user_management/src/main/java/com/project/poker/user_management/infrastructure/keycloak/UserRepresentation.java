package com.project.poker.user_management.infrastructure.keycloak;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRepresentation {
    private String email;
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String username;
}
