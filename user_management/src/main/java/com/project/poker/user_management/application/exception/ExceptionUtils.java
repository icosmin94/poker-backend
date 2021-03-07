package com.project.poker.user_management.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

@Slf4j
public class ExceptionUtils {

    public static Mono<KeyCloakException> throwException(ClientResponse clientResponse) {

        return clientResponse.bodyToMono(String.class)
                .doOnNext(log::error)
                .map(KeyCloakException::new);

    }
}
