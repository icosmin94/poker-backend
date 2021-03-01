package com.project.poker.user_management.infrastructure.keycloak;

import com.project.poker.user_management.application.webclient.WebClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeyCloakClientProperties extends WebClientProperties {
}
