package com.graphql.praphqlplay.lec12;

import graphql.ExecutionInput;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
@Slf4j
public class OperationCachingConfig {

    /*
     * request body
     * exe doc
     * parse  (needs caching)
     * validation (needs caching)
     * exe doc
     *
     * suggestion:
     * use: variables along with operation name
     * use caffeine cache: https://github.com/ben-manes/caffenie
     */

    @Bean
    public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer(PreparsedDocumentProvider provider){

        //This GraphQlSourceBuilderCustomizer is functional interface
//        return (GraphQlSource.SchemaResourceBuilder builder1) -> builder1.configureGraphQl(provide -> provide.preparsedDocumentProvider(provider));

        return c -> c.configureGraphQl(builder -> builder.preparsedDocumentProvider(provider));
    }

    @Bean
    public PreparsedDocumentProvider provider(){

        Map<String, PreparsedDocumentEntry> map =new HashMap<>();

        return new PreparsedDocumentProvider() {

            @Override
            public PreparsedDocumentEntry getDocument(ExecutionInput executionInput, Function<ExecutionInput, PreparsedDocumentEntry> parseAndValidateFunction) {
                log.info("Calling preparsed document");

                //Better improved version
                return map.computeIfAbsent(executionInput.getQuery(), key -> {
                    log.info("Query not found in cache............");
                    var r = parseAndValidateFunction.apply(executionInput);
                    log.info("Caching this query: {}", r);
                    return r;
                });

//                String query = executionInput.getQuery();
//
//                if(map.containsKey(executionInput.getQuery())){
//                    log.info("Found in the cache {}", query);
//                    return map.get(executionInput.getQuery());
//                }
//                log.info("Not cached ... caching it {}", query);
//                PreparsedDocumentEntry preparsedDocumentEntry = parseAndValidateFunction.apply(executionInput);
//                map.put(executionInput.getQuery(), preparsedDocumentEntry);
//                return preparsedDocumentEntry;
            }
        };
    }
}
