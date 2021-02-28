package com.project.poker.user_management.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "Hello user management";
    }

    @GetMapping("test2")
    public String test2() {
        return "Doesn't work";
    }
}
