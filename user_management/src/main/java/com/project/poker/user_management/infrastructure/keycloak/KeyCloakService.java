package com.project.poker.user_management.infrastructure.keycloak;

import com.project.poker.user_management.application.exception.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeyCloakService {

    private static final String USER_URI = "/users";

    private final WebClient keyCloakWebClient;

    public Mono<Void> createUser(UserRepresentation userRepresentation) {

        return keyCloakWebClient.post()
                .uri(uriBuilder -> uriBuilder.path(USER_URI).build())
                .body(BodyInserters.fromValue(userRepresentation))
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtils::throwException)
                .bodyToMono(Void.class);
    }

    public Mono<List<UserRepresentation>> getAllUsersByUserName(String userName) {
        MultiValueMap<String, String> options = new LinkedMultiValueMap<>();
        options.add("username", userName);

        return keyCloakWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(USER_URI).queryParams(options).build())
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtils::throwException)
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}
