package com.project.poker.rsocket_server.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Multimap;
import com.project.poker.commonlib.model.CardColor;
import com.project.poker.commonlib.model.CardNumber;
import com.project.poker.commonlib.model.GenericEvent;
import com.project.poker.commonlib.service.TableEventFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardSendExample implements CommandLineRunner {

    private final TableConnectionHandlerService tableConnectionHandlerService;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Random ran = new Random();

    @Override
    public void run(String... args) throws Exception {
        Multimap<String, RSocketRequester> tableMap = tableConnectionHandlerService.getTableMap();
        AtomicReference<Integer> position = new AtomicReference<>();
        position.set(0);
        while (true) {

            GenericEvent<?> genericEvent;
            switch (position.get()) {
                case 0:
                    genericEvent = TableEventFactory.aCardFlopEvent(generateRandomCard(), generateRandomCard(), generateRandomCard());
                    break;
                case 1:
                    genericEvent = TableEventFactory.aCardTurnEvent(generateRandomCard());
                    break;
                case 2:
                    genericEvent = TableEventFactory.aCardRiverEvent(generateRandomCard());
                    break;
                default:
                    genericEvent = TableEventFactory.aResetTableEvent();
            }
            GenericEvent<?> event = genericEvent;

            tableMap.keys().forEach(table -> {

                tableMap.get(table).forEach(rSocketRequester -> {
                    try {
                        log.info(objectMapper.writeValueAsString(event));
                        rSocketRequester
                                .route("say.hello")
                                .data(objectMapper.writeValueAsString(event))
                                .send()
                                .subscribe();
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
            });

            Thread.sleep(2000);
            position.set((position.get() + 1) % 4);
        }
    }

    public static String generateRandomCard() {
        String cardValue = CardNumber.values()[Math.abs(ran.nextInt()) % CardNumber.values().length].getValue();
        String cardColor = CardColor.values()[Math.abs(ran.nextInt()) % CardColor.values().length].getValue();
        return cardValue + cardColor;
    }
}
