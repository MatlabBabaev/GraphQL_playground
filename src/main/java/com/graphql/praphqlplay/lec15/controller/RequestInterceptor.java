package com.graphql.praphqlplay.lec15.controller;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class RequestInterceptor implements WebGraphQlInterceptor {

    @Override //If client has to pass header
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {

        var headers = request.getHeaders().getOrEmpty("caller-Id");
        var callerId = headers.isEmpty() ? "" : headers.get(0);
        request.configureExecutionInput((e, b) -> b.graphQLContext(Map.of("caller-Id", callerId)).build());
        return chain.next(request);
    }
}
