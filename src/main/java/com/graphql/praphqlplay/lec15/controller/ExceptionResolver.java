package com.graphql.praphqlplay.lec15.controller;

import com.graphql.praphqlplay.lec15.exceptions.ApplicationException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * This is similar to ControllerAdvice
 */
@Service
public class ExceptionResolver implements DataFetcherExceptionResolver {
    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        var ex = toApplicationException(exception);
        return Mono.just(
                List.of(
                        GraphqlErrorBuilder.newError()
                                .message(ex.getMessage())
                                .extensions(ex.getExtentions())
                                .errorType(ex.getErrorType())
                                .build()
                )
        );
    }

    private ApplicationException toApplicationException(Throwable throwable) {
        return ApplicationException.class.equals(throwable.getClass()) ?
                (ApplicationException)throwable :
                new ApplicationException(ErrorType.INTERNAL_ERROR, throwable.getMessage(), Collections.emptyMap());
    }
}
