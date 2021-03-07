package com.project.poker.user_management.application.mapper;

import com.project.poker.user_management.api.model.UserRepresentationDto;
import com.project.poker.user_management.application.domain.model.User;

public class UserMapper {
    public static User fromUserRepresentation(UserRepresentationDto userRepresentationDto) {
        return User.builder()
                .id(userRepresentationDto.getId())
                .email(userRepresentationDto.getEmail())
                .enabled(userRepresentationDto.isEnabled())
                .firstName(userRepresentationDto.getFirstName())
                .lastName(userRepresentationDto.getLastName())
                .username(userRepresentationDto.getUsername())
                .build();
    }
}
