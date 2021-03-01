package com.project.poker.user_management.infrastructure.keycloak;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class KeyCloakServiceTest {

    @Autowired
    public KeyCloakService keyCloakService;

    @Test
    void createUser() {
    }

    @Test
    void getAllUsersByUserName() {
        var users = keyCloakService.getAllUsersByUserName("test123").block();
        System.out.println();
    }
}