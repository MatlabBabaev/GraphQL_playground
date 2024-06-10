package com.graphql.praphqlplay.lec04.service;

import com.graphql.praphqlplay.lec04.entity.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {
    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "Sam", 20, "Wollongong"),
            Customer.create(2, "Mike", 25, "BlackTown"),
            Customer.create(3, "Ota", 35, "Parramatta"),
            Customer.create(4, "Jay-Jay", 18, "CBD")
    );
    public Flux<Customer> allCustomers(){
        return flux;
    }
}
