package com.project.poker.table_manangement.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokerTableDto {
    private Integer id;
    private String tableName;
    private Integer numberOfPlayers;
    private Integer bigBlind;
}
