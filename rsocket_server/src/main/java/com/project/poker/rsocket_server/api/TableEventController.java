package com.project.poker.rsocket_server.api;

import com.project.poker.commonlib.security.UserDetails;
import com.project.poker.rsocket_server.application.service.TableConnectionHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import javax.annotation.PreDestroy;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TableEventController {


    private final TableConnectionHandlerService tableConnectionHandlerService;


    @PreDestroy
    void shutdown() {
        log.info("Detaching all remaining clients...");
        tableConnectionHandlerService.closeAllConnections();
        log.info("Shutting down.");
    }

    @ConnectMapping("table-connection.{tableId}")
    void connectToTableEventStream(RSocketRequester requester, @AuthenticationPrincipal UserDetails userDetails,
                                   @DestinationVariable("tableId") String tableId) {

        requester.rsocket()
                .onClose()
                .log()
                .doFirst(() -> {
                    log.info("Client: {} CONNECTED.", userDetails.getUserId());
                    tableConnectionHandlerService.addRequester(userDetails, tableId, requester);
                })
                .doOnError(error -> {
                    log.warn("Channel to client {} CLOSED", userDetails.getUserId());
                })
                .doFinally(consumer -> {
                    tableConnectionHandlerService.removeRequester(userDetails, tableId, requester);
                    log.info("Client {} DISCONNECTED", userDetails.getUserId());
                })
                .subscribe();
    }

}
