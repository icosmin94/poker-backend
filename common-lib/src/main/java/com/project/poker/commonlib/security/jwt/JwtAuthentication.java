package com.project.poker.commonlib.security.jwt;

import com.project.poker.commonlib.security.UserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private Object principal;

    JwtAuthentication(UserDetails principal) {
        super(null);
        this.principal = principal;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
