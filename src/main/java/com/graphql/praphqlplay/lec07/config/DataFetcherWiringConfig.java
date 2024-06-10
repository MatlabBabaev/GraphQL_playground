package com.graphql.praphqlplay.lec07.config;

import com.graphql.praphqlplay.lec07.service.CustomerOrderDataFetcher;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.Map;

@Configuration
public class DataFetcherWiringConfig {

    @Autowired
    private CustomerOrderDataFetcher dataFetcher;

    @Bean
    public RuntimeWiringConfigurer configurer(){
        //Here we are manually wiring the bean.
        //Here we can wire the type in the schema
        return c -> c.type("Query", b -> b.dataFetchers(map()));
    }

    private Map<String, DataFetcher> map(){
        return Map.of(
                "customers", dfe -> "s",
                "age", dfe -> 12,
                "city", dfe -> "sydney"
        );
    }
}
