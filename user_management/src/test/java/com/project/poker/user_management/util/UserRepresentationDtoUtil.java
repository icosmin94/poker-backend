package com.project.poker.user_management.util;

import com.project.poker.user_management.api.model.UserRepresentationDto;

import java.util.UUID;

public class UserRepresentationDtoUtil {

    public static UserRepresentationDto aUserRepresentation() {
        return UserRepresentationDto.builder()
                .email(UUID.randomUUID().toString() + "@test.com")
                .username(UUID.randomUUID().toString())
                .firstName("Testing")
                .lastName("Forever")
                .password("AP@ss123")
                .build();
    }
}
