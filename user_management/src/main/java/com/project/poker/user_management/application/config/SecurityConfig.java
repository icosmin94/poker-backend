package com.project.poker.user_management.application.config;

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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.poker.user_management.api.UserController.REGISTER_USER_URI;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ServerProperties serverProperties;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        var registerUserUrl = serverProperties.getServlet().getContextPath() + REGISTER_USER_URI;
        var serverWebExchangeMatchers = new ArrayList<ServerWebExchangeMatcher>();
        serverWebExchangeMatchers.add(ServerWebExchangeMatchers.pathMatchers(registerUserUrl));
        
        http
                .securityMatcher(negate(serverWebExchangeMatchers))
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                );
        return http.build();
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
