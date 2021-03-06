package com.project.poker.user_management.application.mapper;

import com.project.poker.user_management.api.model.UserRepresentationDto;
import com.project.poker.user_management.infrastructure.keycloak.model.UserRepresentation;

public class UserRepresentationMapper {

    public static UserRepresentation toUserRepresentation(UserRepresentationDto userRepresentationDto) {
        return UserRepresentation.builder()
                .email(userRepresentationDto.getEmail())
                .id(userRepresentationDto.getId())
                .enabled(userRepresentationDto.isEnabled())
                .firstName(userRepresentationDto.getFirstName())
                .lastName(userRepresentationDto.getLastName())
                .username(userRepresentationDto.getUsername())
                .build();
    }
}
