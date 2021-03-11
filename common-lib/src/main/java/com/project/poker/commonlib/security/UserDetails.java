package com.project.poker.commonlib.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDetails {

    private String userId;
    private String email;
    private String name;
}
