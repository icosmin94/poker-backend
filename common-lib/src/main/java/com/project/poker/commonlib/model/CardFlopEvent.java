package com.project.poker.commonlib.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardFlopEvent {
    private String cardName;
    private Integer cardPosition;
}
