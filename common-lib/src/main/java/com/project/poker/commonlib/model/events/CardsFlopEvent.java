package com.project.poker.commonlib.model.events;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardsFlopEvent {
    private List<String> cardNames;
}
