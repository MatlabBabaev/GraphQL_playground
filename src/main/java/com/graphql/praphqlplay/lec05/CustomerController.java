package com.graphql.praphqlplay.lec05;

import com.graphql.praphqlplay.lec05.entity.Customer;
import com.graphql.praphqlplay.lec05.service.CustomerService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @QueryMapping
    public Flux<Customer> customers(DataFetchingEnvironment environment){
        System.out.println("customer: environment: -> " + environment.getDocument());
        System.out.println(environment.getOperationDefinition());
        return this.service.allCustomers();
    }
}
