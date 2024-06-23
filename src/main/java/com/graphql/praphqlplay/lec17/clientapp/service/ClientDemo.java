package com.graphql.praphqlplay.lec17.clientapp.service;

import com.graphql.praphqlplay.lec17.clientapp.client.CustomerClient;
import com.graphql.praphqlplay.lec17.clientapp.client.SubscriptionClient;
import com.graphql.praphqlplay.lec17.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ClientDemo implements CommandLineRunner {

    @Autowired
    private CustomerClient client;

    @Autowired
    private SubscriptionClient subscriptionClient;

    @Override
    public void run(String... args) throws Exception {

        subscriptionClient
                .customerEvents()
                .doOnNext(e -> System.out.println("*** " + e.toString() + " ***"))
                        .subscribe();

        allCustomersDemo()
                .then(this.customersDemoById())
                .then(this.creeateCustomersDemo())
                .then(this.updateCustomersDemo())
                .then(this.deleteCustomerDemo())
                .subscribe();
    }

    private <T> Mono<Void> executor(String message, Mono<T> publisher){
        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(()-> System.out.println(message))
                .then(publisher)
                .doOnNext(System.out::println)
                .then();
    }

    private Mono<Void> allCustomersDemo(){
        return this.executor("---all Customers demo: ---", this.client.allCustomers());
    }
    private Mono<Void> customersDemoById(){
        return this.executor("---Customer By Id demo: ---", this.client.getCustomerById(2));
    }

    private Mono<Void> creeateCustomersDemo(){
        return this.executor("---Customer create demo: ---", this.client.createCustomer(CustomerDto.create(null, "Samandar", 35, "Andizhan")));
    }
    private Mono<Void> updateCustomersDemo(){
        return this.executor("---Customer update demo: ---", this.client.updateCustomer(
                3,
                CustomerDto.create(null,  "Jurobek", 15, "Qarshi")));
    }
    private Mono<Void> deleteCustomerDemo(){
        return this.executor("---Customer deletion demo: ---", this.client.deleteCustomer(55));
    }
}
