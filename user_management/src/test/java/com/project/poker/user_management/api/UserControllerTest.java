package com.project.poker.user_management.api;

import com.project.poker.user_management.util.UserRepresentationDtoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static com.project.poker.user_management.api.UserController.REGISTER_USER_URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    public WebTestClient webTestClient;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    public void registerUser() {
        webTestClient.post()
                .uri(contextPath + REGISTER_USER_URI)
                .body(BodyInserters.fromValue(UserRepresentationDtoUtil.aUserRepresentation()))
                .exchange()
                .expectStatus().is2xxSuccessful();
    }
}