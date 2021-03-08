package com.project.poker.commonlib.exception;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                          ResourceProperties resourceProperties,
                                          ApplicationContext applicationContext, ServerCodecConfigurer codecConfigurer) {

        super(errorAttributes, resourceProperties, applicationContext);
        this.setMessageWriters(codecConfigurer.getWriters());
        this.setMessageReaders(codecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {

        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request)  {
        try {

            Throwable exception = getError(request);

            Method exceptionHandler = null;

            for (Method method : GlobalExceptionHandler.class.getMethods()) {
                if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length > 0
                        && (method.getParameterTypes()[0]).equals(exception.getClass())) {
                    exceptionHandler = method;
                    break;
                }
            }
            if (exceptionHandler == null) {
                exceptionHandler = GlobalExceptionHandler.class.getDeclaredMethod("handleException", Exception.class);
            }

            ResponseEntity<BackendErrorResponse> errorResponse = (ResponseEntity<BackendErrorResponse>)
                    exceptionHandler.invoke(null, exception);

            return ServerResponse.status(errorResponse.getStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(errorResponse.getBody()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
