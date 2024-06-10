package com.graphql.praphqlplay.lec02.service;

import com.graphql.praphqlplay.lec02.entity.AgeRangeFilter;
import com.graphql.praphqlplay.lec02.entity.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "sam", 20, "Wollongong"),
            Customer.create(2, "Josh", 25, "BlackTown"),
            Customer.create(3, "Ota", 35, "Parramatta"),
            Customer.create(4, "Jaym", 18, "CBD")
    );
    public Flux<Customer> allCustomers(){
        return flux;
    }
    public Mono<Customer> customerById(Integer id) {
        return flux.filter(c -> c.getId().equals(id))
                .next();
    }
    public Flux<Customer> nameContains(String name){
        return flux.filter(c -> c.getName().equals(name));
    }

    public Flux<Customer> withinAge(AgeRangeFilter filter){
        return flux
                .filter(c -> c.getAge()>= filter.getMinAge() && c.getAge()<=filter.getMaxAge());
    }
}
