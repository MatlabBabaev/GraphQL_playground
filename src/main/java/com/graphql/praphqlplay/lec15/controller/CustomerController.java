package com.graphql.praphqlplay.lec15.controller;

import com.graphql.praphqlplay.lec15.dto.CustomerDto;
import com.graphql.praphqlplay.lec15.dto.CustomerNotFound;
import com.graphql.praphqlplay.lec15.dto.DeleteResponseDto;
import com.graphql.praphqlplay.lec15.exceptions.ApplicationErrors;
import com.graphql.praphqlplay.lec15.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @QueryMapping
    public Flux<CustomerDto> customers(){
        return this.service.allCustomers();
    }

//    @QueryMapping
//    public Mono<CustomerDto> customerById(@Argument Integer id){
//        return this.service.findById(id)
//                .switchIfEmpty(Mono.just(CustomerResponse.create(id)))
////                .switchIfEmpty(ApplicationErrors.noSuchUser(id));
//    }
    @QueryMapping
    public Mono<Object> customerById(@Argument Integer id){
        return this.service.findById(id)
                .cast(Object.class)
                .switchIfEmpty(Mono.just(CustomerNotFound.create(id)));
    }


    @MutationMapping
    public Mono<CustomerDto> createCustomer(@Argument CustomerDto customer){
        return
                Mono.just(customer)
                        .filter(c -> c.getAge()>=18)
                        .flatMap(this.service::createCustomer)
                        .switchIfEmpty(ApplicationErrors.ageNotAllowed(customer));
    }

    @MutationMapping
    public Mono<CustomerDto> updateCustomer(@Argument Integer id, @Argument("customer") CustomerDto dto){
        return this.service.updateCustomer(id, dto);
    }

    @MutationMapping
    public Mono<DeleteResponseDto> deleteCustomer(@Argument Integer id){
        return  this.service.deleteCustomer(id);
    }
}
