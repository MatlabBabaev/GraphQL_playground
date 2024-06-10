package com.graphql.praphqlplay.lec05.service;

import com.graphql.praphqlplay.lec05.entity.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
}
