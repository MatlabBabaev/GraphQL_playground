package com.graphql.praphqlplay.lec03;

import com.graphql.praphqlplay.lec03.entity.Customer;
import com.graphql.praphqlplay.lec03.entity.CustomerOrderDto;
import com.graphql.praphqlplay.lec03.service.CustomerService;
import com.graphql.praphqlplay.lec03.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private OrderService orderService;

    @SchemaMapping(typeName = "Query") // This is the same as QueryMapping
    public Flux<Customer> customers(){
        return this.service.allCustomers();
    }

    @SchemaMapping(typeName = "Customer") // this is something like CustomerMapping
    public Flux<CustomerOrderDto> orders(Customer customer, @Argument Integer limit){
        return this.orderService.ordersByCustomerName(customer.getName())
                .take(limit);
    }
}
