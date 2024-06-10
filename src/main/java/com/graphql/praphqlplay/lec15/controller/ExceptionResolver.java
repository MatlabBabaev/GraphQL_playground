package com.graphql.praphqlplay.lec15.controller;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * This is similar to ControllerAdvice
 */
@Service
public class ExceptionResolver implements DataFetcherExceptionResolver {
    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        return Mono.just(
                List.of(
                        GraphqlErrorBuilder.newError()
                                .message(exception.getMessage())
                                .errorType(ErrorType.INTERNAL_ERROR)
                                .build()
                )
        );
    }
}
