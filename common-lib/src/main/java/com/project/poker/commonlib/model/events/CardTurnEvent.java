package com.project.poker.commonlib.model.events;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardTurnEvent {
    private String cardName;
}
