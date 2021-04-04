package com.project.poker.rsocket_server.application.service;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.project.poker.commonlib.security.UserDetails;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;

@Service
public class TableConnectionHandlerService {

    private final Multimap<String, RSocketRequester> clientsMap = Multimaps.synchronizedMultimap(LinkedHashMultimap.create());

    public void closeAllConnections() {
        clientsMap.values().forEach(requester -> requester.rsocket().dispose());
    }

    public void addRequester(UserDetails userDetails, RSocketRequester requester) {
        clientsMap.put(userDetails.getUserId(), requester);
    }

    public void removeRequester(UserDetails userDetails, RSocketRequester requester) {
        clientsMap.remove(userDetails.getUserId(), requester);
    }
}
