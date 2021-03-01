package com.project.poker.user_management.application.webclient;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class WebClientProperties {
    private String url;
    private int idleTimeoutSec;
    private int connectionPoolMaxSize;
    private Map<String, String> headers = new HashMap<>();
    private int connectionReadTimeoutSec;
    private int connectionConnectTimeoutSec;
}
