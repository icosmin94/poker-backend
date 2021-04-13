package com.project.poker.commonlib.service;

import com.project.poker.commonlib.model.EventName;
import com.project.poker.commonlib.model.GenericEvent;
import com.project.poker.commonlib.model.events.CardRiverEvent;
import com.project.poker.commonlib.model.events.CardTurnEvent;
import com.project.poker.commonlib.model.events.CardsFlopEvent;
import com.project.poker.commonlib.model.events.ResetTableEvent;

import java.util.List;

public class TableEventFactory {

    public static GenericEvent<ResetTableEvent> aResetTableEvent() {
        return GenericEvent.<ResetTableEvent>builder()
                .eventName(EventName.RESET_TABLE)
                .timeStamp(System.currentTimeMillis())
                .payload(new ResetTableEvent())
                .build();
    }

    public static GenericEvent<CardsFlopEvent> aCardFlopEvent(String cardName1, String cardName2, String cardName3) {
        return GenericEvent.<CardsFlopEvent>builder()
                .eventName(EventName.CARDS_FLOP)
                .timeStamp(System.currentTimeMillis())
                .payload(new CardsFlopEvent(List.of(cardName1, cardName2, cardName3)))
                .build();
    }

    public static GenericEvent<CardTurnEvent> aCardTurnEvent(String cardName) {
        return GenericEvent.<CardTurnEvent>builder()
                .eventName(EventName.CARD_TURN)
                .timeStamp(System.currentTimeMillis())
                .payload(new CardTurnEvent(cardName))
                .build();
    }

    public static GenericEvent<CardRiverEvent> aCardRiverEvent(String cardName) {
        return GenericEvent.<CardRiverEvent>builder()
                .eventName(EventName.CARD_RIVER)
                .timeStamp(System.currentTimeMillis())
                .payload(new CardRiverEvent(cardName))
                .build();
    }
}
