package com.graphql.praphqlplay.lec04.service;

import com.graphql.praphqlplay.lec04.entity.Customer;
import com.graphql.praphqlplay.lec04.entity.CustomerOrderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {

    private final Map<String, List<CustomerOrderDto>> map = Map.of(
            "Sam", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "sam-product-1"),
                    CustomerOrderDto.create(UUID.randomUUID(), "sam-product-2")
            ),
            "Mike", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "mikle-product-1"),
                    CustomerOrderDto.create(UUID.randomUUID(), "mike-product-2"),
                    CustomerOrderDto.create(UUID.randomUUID(), "mike-product-3")
            )
    );
    public Flux<CustomerOrderDto> ordersByCustomerName(String name){

        //Returns the value to which the specified key is mapped, or defaultValue
        // if this map contains no mapping for the key
        return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
    }

    public Flux<List<CustomerOrderDto>> ordersByCustomerName(List<String> names){
        return Flux.fromIterable(names)
                        .flatMapSequential(n -> this.fetchOrders(n).defaultIfEmpty(Collections.emptyList()));
    }

    //some source
    public Mono<List<CustomerOrderDto>> fetchOrders(String name){
        return Mono.justOrEmpty(map.get(name))
                .delayElement(Duration.ofMillis(ThreadLocalRandom.current().nextInt(0,500)));
    }

    public Mono<Map<Customer, List<CustomerOrderDto>>> fetchOrdersAsMap(List<Customer> customers){
        return Flux.fromIterable(customers)
                .map(c -> Tuples.of(c, map.getOrDefault(c.getName(), Collections.emptyList())))
                .collectMap(
                        Tuple2::getT1,
                        Tuple2::getT2
                );
    }
}
