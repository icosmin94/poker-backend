package com.project.poker.user_management.api;

import com.project.poker.user_management.infrastructure.keycloak.KeyCloakService;
import com.project.poker.user_management.infrastructure.keycloak.UserRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {
    public static final String REGISTER_USER_URI = "/user/register";

    private final KeyCloakService keyCloakService;

    @PostMapping(REGISTER_USER_URI)
    public Mono<Void> registerUser(@RequestBody UserRepresentation userRepresentation) {
        return keyCloakService.createUser(userRepresentation);
    }

}
