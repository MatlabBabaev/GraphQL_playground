package com.graphql.praphqlplay.lec15.exceptions;


import lombok.Getter;
import org.springframework.graphql.execution.ErrorType;

import java.util.Map;

@Getter
public class ApplicationException extends RuntimeException {

    private final ErrorType errorType;
    private final String message;
    private final Map<String, Object> extentions;

    public ApplicationException(ErrorType errorType, String message, Map<String, Object> extensions) {
        this.errorType = errorType;
        this.message = message;
        this.extentions = extensions;
    }
}
