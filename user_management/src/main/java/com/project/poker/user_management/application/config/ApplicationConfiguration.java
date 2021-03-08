package com.project.poker.user_management.application.config;

import com.project.poker.commonlib.configuration.ExceptionConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ExceptionConfiguration.class)
public class ApplicationConfiguration {

}
