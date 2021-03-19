package com.project.poker.table_manangement.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "POKER_TABLE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokerTable {

    @Id
    private Integer id;
    private String tableName;
    private Integer numberOfPlayers;
    private Integer bigBlind;
}
