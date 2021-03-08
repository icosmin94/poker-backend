package com.project.poker.commonlib.configuration;

import com.project.poker.commonlib.exception.GlobalErrorWebExceptionHandler;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

@Configuration
public class ExceptionConfiguration {

    @Bean
    public GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                                                         ResourceProperties resourceProperties,
                                                                         ApplicationContext applicationContext,
                                                                         ServerCodecConfigurer codecConfigurer) {
        return new GlobalErrorWebExceptionHandler(errorAttributes, resourceProperties,
                applicationContext, codecConfigurer);
    }
}
