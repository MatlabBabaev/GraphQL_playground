package com.graphql.praphqlplay.lec15.exceptions;

import com.graphql.praphqlplay.lec15.dto.CustomerDto;
import org.springframework.graphql.execution.ErrorType;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ApplicationErrors {

    public static <T> Mono<T> noSuchUser(Integer id) {
        return Mono.error(new ApplicationException(ErrorType.BAD_REQUEST, "No such user", Map.of("customerId", id)));
    }

    public static <T> Mono<T> ageNotAllowed(CustomerDto dto) {
        return Mono.error(new ApplicationException(ErrorType.BAD_REQUEST, "Age must be 18 or older", Map.of("input", dto)));
    }
}
