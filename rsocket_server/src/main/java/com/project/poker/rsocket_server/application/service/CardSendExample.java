package com.project.poker.rsocket_server.application.service;

import com.google.common.collect.Multimap;
import com.project.poker.commonlib.model.CardColor;
import com.project.poker.commonlib.model.CardFlopEvent;
import com.project.poker.commonlib.model.CardNumber;
import io.rsocket.util.DefaultPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardSendExample implements CommandLineRunner {

    private final TableConnectionHandlerService tableConnectionHandlerService;

    @Override
    public void run(String... args) throws Exception {
        Multimap<String, RSocketRequester> tableMap = tableConnectionHandlerService.getTableMap();
        AtomicReference<Integer> position = new AtomicReference<>();
        position.set(0);
        Random ran = new Random();
        while (true) {
            Thread.sleep(2000);
            tableMap.keys().forEach(table -> {
                String cardValue = CardNumber.values()[Math.abs(ran.nextInt()) % CardNumber.values().length].getValue();
                String cardColor = CardColor.values()[Math.abs(ran.nextInt()) % CardColor.values().length].getValue();
                CardFlopEvent cardFlopEvent = new CardFlopEvent(cardValue + cardColor, position.get());
                log.info(cardFlopEvent.toString());
                position.set((position.get() + 1) % 5);
                tableMap.get(table).forEach(rSocketRequester -> {
                    rSocketRequester.rsocket().fireAndForget(DefaultPayload.create(cardFlopEvent.toString()))
                            .log()
                            .subscribe();
                });
            });
        }
    }
}
