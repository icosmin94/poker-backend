package com.project.poker.user_management.infrastructure.keycloak;

import com.project.poker.user_management.api.model.UserRepresentationDto;
import com.project.poker.user_management.application.exception.ExceptionUtils;
import com.project.poker.user_management.application.exception.UserAlreadyRegisteredException;
import com.project.poker.user_management.application.mapper.UserMapper;
import com.project.poker.user_management.application.mapper.UserRepresentationMapper;
import com.project.poker.user_management.application.services.UserService;
import com.project.poker.user_management.infrastructure.keycloak.model.UpdateUserCommand;
import com.project.poker.user_management.infrastructure.keycloak.model.UserRepresentation;
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
    private static final String SET_PASSWORD_URI = "/users/{userId}/reset-password";

    private final WebClient keyCloakWebClient;
    private final UserService userService;

    public Mono<Void> createUser(UserRepresentationDto userRepresentationDto) {
        if (userService.findUserByUserName(userRepresentationDto.getUsername()).isPresent()) {
            throw new UserAlreadyRegisteredException("User with username " +
                    userRepresentationDto.getUsername() + " already registered" );
        }

        UserRepresentation userRepresentation = UserRepresentationMapper.toUserRepresentation(userRepresentationDto);
        UpdateUserCommand updateUserCommand = UpdateUserCommand
                .anUpdatePasswordCommand(userRepresentationDto.getPassword());

        return postUserToKeyCloak(userRepresentation)
                .then(Mono.defer(() -> getAllUsersByUserName(userRepresentation.getUsername())
                                        .doOnNext(users -> userService.saveUser(UserMapper
                                                .fromUserRepresentation(users.get(0))))
                                        .flatMap(users -> updatePassword(updateUserCommand,
                                                users.get(0).getId()))));
    }

    public Mono<Void> updatePassword(UpdateUserCommand updateUserCommand,  String userId) {
        return keyCloakWebClient.put()
                .uri(uriBuilder -> uriBuilder.path(SET_PASSWORD_URI).build(userId))
                .body(BodyInserters.fromValue(updateUserCommand))
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtils::throwException)
                .bodyToMono(Void.class);
    }

    public Mono<Void> postUserToKeyCloak(UserRepresentation userRepresentation) {
        return keyCloakWebClient.post()
                .uri(uriBuilder -> uriBuilder.path(USER_URI).build())
                .body(BodyInserters.fromValue(userRepresentation))
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtils::throwException)
                .bodyToMono(Void.class);
    }

    public Mono<List<UserRepresentationDto>> getAllUsersByUserName(String userName) {
        MultiValueMap<String, String> options = new LinkedMultiValueMap<>();
        options.add("username", userName);

        return keyCloakWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(USER_URI).queryParams(options).build())
                .retrieve()
                .onStatus(HttpStatus::isError, ExceptionUtils::throwException)
                .bodyToMono(new ParameterizedTypeReference<List<UserRepresentationDto>>() {});
    }
}
