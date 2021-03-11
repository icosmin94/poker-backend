package com.project.poker.commonlib.security.jwt;

import com.project.poker.commonlib.security.UserDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

import java.util.Map;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, Mono<? extends AbstractAuthenticationToken>> {


    @Override
    public Mono<? extends AbstractAuthenticationToken> convert(Jwt source) {
        Map<String, Object> map = source.getClaims();

        UserDetails userDetails = UserDetails.builder()
                .email((String)map.get(JwtClaims.EMAIL))
                .name((String)map.get(JwtClaims.NAME))
                .userId((String)map.get(JwtClaims.SUBJECT))
                .build();

        return Mono.just(new JwtAuthentication(userDetails));
    }
}
