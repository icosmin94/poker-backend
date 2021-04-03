package com.project.poker.rsocket_server.api;

import com.nimbusds.jwt.JWT;
import com.project.poker.commonlib.security.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class TableEventController {

    private final Map<String, RSocketRequester> clientsMap = new HashMap<>();

    @PreDestroy
    void shutdown() {
        log.info("Detaching all remaining clients...");
        clientsMap.values().forEach(requester -> requester.rsocket().dispose());
        log.info("Shutting down.");
    }

    @ConnectMapping({"table-connection"})
    void connectToTableEventStream(RSocketRequester requester, @AuthenticationPrincipal Object object) {

        requester.rsocket()
                .onClose()
                .log()
                .doFirst(() -> {
                    log.info("Client: {} CONNECTED.", "gigi");
                    clientsMap.put("gigi", requester);
                })
                .doOnError(error -> {
                    log.warn("Channel to client {} CLOSED", "gigi");
                })
                .doFinally(consumer -> {
                    clientsMap.put("gigi", requester);
                    log.info("Client {} DISCONNECTED", "gigi");
                })
                .subscribe();
    }

}
