package com.project.poker.rsocket_server.application.config;

import com.project.poker.commonlib.configuration.CommonConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CommonConfiguration.class)
public class ApplicationConfiguration {

}
