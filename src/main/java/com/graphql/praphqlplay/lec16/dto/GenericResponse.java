package com.graphql.praphqlplay.lec16.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.graphql.ResponseError;

@ToString
@Getter
public class GenericResponse<T> {

    private final T data;
    private final ResponseError error;
    private final boolean dataPresent;

    public GenericResponse(T data) {
        this.data = data;
        this.dataPresent = true;
        this.error = null;
    }

    public GenericResponse(ResponseError error) {
        this.data = null;
        this.dataPresent = false;
        this.error = error;
    }
}
