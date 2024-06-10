package com.graphql.praphqlplay.lec10.dto;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Controller
public class ProductController {

    @QueryMapping
    public Flux<Object> products(){

        return Flux.just(
                FruitDto.create("banana", 1, LocalDate.now().plusDays(3)),
                FruitDto.create("apple", 1, LocalDate.now().plusDays(15)),
                Electronics.create("Dell", 600, "APPLE"),
                Electronics.create("APPLE", 590, "SAMSUNG"),
                Book.create("Java", 40, "Matlab")
        );
    }
}
