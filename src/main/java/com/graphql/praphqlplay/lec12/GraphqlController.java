package com.graphql.praphqlplay.lec12;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class GraphqlController {

    @QueryMapping()
    public Mono<String> sayHello(@Argument("name") String value){
        return Mono.fromSupplier(() ->"Hello" + value);
    }
}
