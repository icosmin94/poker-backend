package com.project.poker.user_management.application.webclient;

import com.project.poker.user_management.infrastructure.keycloak.KeyCloakClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientsConfiguration {

    @Bean
    public WebClient keyCloakWebClient(KeyCloakClientProperties keyCloakClientProperties,
                                       ReactiveClientRegistrationRepository clientRegistrations) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(
                        clientRegistrations,
                        new UnAuthenticatedServerOAuth2AuthorizedClientRepository());
        oauth.setDefaultClientRegistrationId("keycloak");

        WebClient.Builder clientBuilder =  WebClient.builder()
                .baseUrl(keyCloakClientProperties.getUrl())
                .clientConnector(WebClientAssembler.getClientHttpConnector(keyCloakClientProperties))
                .filter(oauth);

        WebClientAssembler.assembleHeaders(clientBuilder, keyCloakClientProperties.getHeaders());

        return clientBuilder.build();
    }
}
