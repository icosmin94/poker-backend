package com.project.poker.rsocket_server.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientTableKey {
    private String userId;
    private String tableId;

    public static ClientTableKey of(String userId, String tableId) {
        return new ClientTableKey(userId, tableId);
    }
}
