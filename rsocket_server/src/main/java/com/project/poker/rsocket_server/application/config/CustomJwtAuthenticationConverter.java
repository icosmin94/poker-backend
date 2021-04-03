package com.project.poker.rsocket_server.application.config;

import com.project.poker.commonlib.security.UserDetails;
import com.project.poker.commonlib.security.jwt.JwtAuthentication;
import com.project.poker.commonlib.security.jwt.JwtClaims;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {

        Map<String, Object> map = source.getClaims();

        UserDetails userDetails = UserDetails.builder()
                .email((String)map.get(JwtClaims.EMAIL))
                .name((String)map.get(JwtClaims.NAME))
                .userId((String)map.get(JwtClaims.SUBJECT))
                .build();

        return new JwtAuthentication(userDetails);
    }

}
