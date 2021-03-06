package com.project.poker.deck_management.application.config;

import com.project.poker.commonlib.security.jwt.CustomJwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt()
                        .jwtAuthenticationConverter(customJwtAuthenticationConverter())
                );
        return http.build();
    }

    @Bean
    CustomJwtAuthenticationConverter customJwtAuthenticationConverter(){
        return new CustomJwtAuthenticationConverter();
    }
}
