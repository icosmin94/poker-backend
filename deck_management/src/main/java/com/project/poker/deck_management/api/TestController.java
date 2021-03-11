package com.project.poker.deck_management.api;

import com.project.poker.commonlib.security.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("test")
    public String test(@AuthenticationPrincipal UserDetails userDetails) {

        return "Hello " + userDetails.getUserId();
    }
}
