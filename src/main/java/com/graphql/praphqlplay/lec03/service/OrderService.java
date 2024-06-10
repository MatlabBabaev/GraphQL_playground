package com.graphql.praphqlplay.lec03.service;

import com.graphql.praphqlplay.lec03.entity.CustomerOrderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
}
