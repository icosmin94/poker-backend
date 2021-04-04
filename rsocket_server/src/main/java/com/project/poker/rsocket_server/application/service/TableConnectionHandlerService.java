package com.project.poker.rsocket_server.application.service;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.project.poker.commonlib.security.UserDetails;
import com.project.poker.rsocket_server.application.model.ClientTableKey;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;

@Service
public class TableConnectionHandlerService {

    private final Multimap<ClientTableKey, RSocketRequester> clientTableMap = Multimaps.synchronizedMultimap(LinkedHashMultimap.create());
    private final Multimap<String, RSocketRequester> tableMap = Multimaps.synchronizedMultimap(LinkedHashMultimap.create());

    public void closeAllConnections() {
        clientTableMap.values().forEach(requester -> requester.rsocket().dispose());
        clientTableMap.clear();
        tableMap.clear();
    }

    public void addRequester(UserDetails userDetails, String tableId, RSocketRequester requester) {
        clientTableMap.put(ClientTableKey.of(userDetails.getUserId(), tableId), requester);
        tableMap.put(tableId, requester);
    }

    public void removeRequester(UserDetails userDetails, String tableId, RSocketRequester requester) {
        clientTableMap.remove(ClientTableKey.of(userDetails.getUserId(), tableId), requester);
        tableMap.values().remove(requester);
    }
}
