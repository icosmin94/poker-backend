package com.project.poker.rsocket_server.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.messaging.handler.invocation.reactive.AuthenticationPrincipalArgumentResolver;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;
import reactor.core.publisher.Mono;

@Configuration
@EnableRSocketSecurity
public class RSocketSecurityConfiguration {

    @Value("${oath2.issuer.location}")
    private String issuerLocation;

    @Bean
    ReactiveJwtDecoder reactiveJwtDecoder() {
        return ReactiveJwtDecoders
                .fromIssuerLocation(issuerLocation);
    }

    @Bean
    PayloadSocketAcceptorInterceptor rsocketInterceptor(RSocketSecurity rsocket) {
        rsocket
                .authorizePayload(authorize ->
                        authorize
                                .anyRequest().authenticated()
                                .anyExchange().permitAll()
                )
                .jwt(jwtSpec -> {
                    try {
                        jwtSpec.authenticationManager(jwtReactiveAuthenticationManager(reactiveJwtDecoder()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return rsocket.build();
    }

    @Bean
    public JwtReactiveAuthenticationManager jwtReactiveAuthenticationManager(ReactiveJwtDecoder reactiveJwtDecoder) {
        JwtReactiveAuthenticationManager jwtReactiveAuthenticationManager = new JwtReactiveAuthenticationManager(reactiveJwtDecoder);
        jwtReactiveAuthenticationManager.setJwtAuthenticationConverter( new ReactiveJwtAuthenticationConverterAdapter(customJwtAuthenticationConverter()));
        return jwtReactiveAuthenticationManager;
    }

    @Bean
    public RSocketMessageHandler messageHandler(RSocketStrategies strategies) {
        RSocketMessageHandler handler = new RSocketMessageHandler();
        handler.getArgumentResolverConfigurer().addCustomResolver(new AuthenticationPrincipalArgumentResolver());
        handler.setRSocketStrategies(strategies);
        return handler;
    }

    @Bean
    CustomJwtAuthenticationConverter customJwtAuthenticationConverter(){
        return new CustomJwtAuthenticationConverter();
    }

    @MessageExceptionHandler
    public Mono<Object> handleException(Exception e) {
        return Mono.just(null);
    }
}
