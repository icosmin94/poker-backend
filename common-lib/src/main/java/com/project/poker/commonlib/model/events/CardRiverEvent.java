package com.project.poker.commonlib.model.events;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRiverEvent {
    private String cardName;
}
