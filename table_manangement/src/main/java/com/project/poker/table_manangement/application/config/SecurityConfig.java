package com.project.poker.table_manangement.application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.AndServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ServerProperties serverProperties;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        http
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                );
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.applyPermitDefaultValues();

        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    private ServerWebExchangeMatcher negate(List<ServerWebExchangeMatcher> serverWebExchangeMatchers) {
        List<ServerWebExchangeMatcher> negatedMatchers = serverWebExchangeMatchers
                .stream()
                .map(NegatedServerWebExchangeMatcher::new)
                .collect(Collectors.toList());
        return new AndServerWebExchangeMatcher(negatedMatchers);
    }

    private ServerWebExchangeMatcher forPortAndPath(final int port, final String path) {
        return new AndServerWebExchangeMatcher(forPort(port), ServerWebExchangeMatchers.pathMatchers(path));
    }

    private ServerWebExchangeMatcher forPort(final int port) {
        return (exchange) -> exchange.getRequest().getURI().getPort() == port ?
                ServerWebExchangeMatcher.MatchResult.match() : ServerWebExchangeMatcher.MatchResult.notMatch();
    }
}
