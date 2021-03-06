package com.project.poker.user_management.application.webclient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebClientAssembler {

    public static WebClient.Builder assembleHeaders(WebClient.Builder builder, Map<String, String> headers) {
        headers.forEach(builder::defaultHeader);
        return builder;
    }

    public static ClientHttpConnector getClientHttpConnector(WebClientProperties webClientProperties) {
        HttpClient httpClient = HttpClient.create();

        httpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, webClientProperties.getConnectionConnectTimeoutSec() * 1_000);
        httpClient.doOnConnected(conn -> conn
                .addHandlerLast(new ReadTimeoutHandler(webClientProperties.getConnectionReadTimeoutSec(), TimeUnit.SECONDS)));

        return new ReactorClientHttpConnector(httpClient);
    }
}
