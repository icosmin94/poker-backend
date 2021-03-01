package com.project.poker.user_management.infrastructure.keycloak;

import com.project.poker.user_management.application.exception.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeyCloakService {

    private static final String USER_REGISTER_URI = "/users";

    private final WebClient keyCloakWebClient;

    public Mono<Void> createUser(UserRepresentation userRepresentation) {

        return keyCloakWebClient.post()
                .uri(uriBuilder -> uriBuilder.path(USER_REGISTER_URI).build())
                .body(BodyInserters.fromValue(userRepresentation))
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtils::throwException)
                .bodyToMono(Object.class)
                .doOnNext(response -> log.info(response.toString()))
                .then();
    }

}
