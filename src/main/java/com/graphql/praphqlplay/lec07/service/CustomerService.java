package com.graphql.praphqlplay.lec07.service;

import com.graphql.praphqlplay.lec07.entity.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CustomerService {
    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "Sam", 20, "Wollongong"),
            Customer.create(2, "Mike", 25, "BlackTown"),
            Customer.create(3, "Ota", 35, "Parramatta"),
            Customer.create(4, "Jay-Jay", 18, "CBD")
    );
    public Flux<Customer> allCustomers(){
        return flux
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(c -> print("customer " + c.getName()));
    }

    private void print(String msg){
        System.out.println(LocalDateTime.now() + " : " + msg);
    }
}
