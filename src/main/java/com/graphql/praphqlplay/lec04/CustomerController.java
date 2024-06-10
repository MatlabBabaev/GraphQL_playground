package com.graphql.praphqlplay.lec04;

import com.graphql.praphqlplay.lec04.entity.Customer;
import com.graphql.praphqlplay.lec04.entity.CustomerOrderDto;
import com.graphql.praphqlplay.lec04.service.CustomerService;
import com.graphql.praphqlplay.lec04.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

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

    //N+1 problem solution
//    @BatchMapping(typeName = "Customer")
//    public Flux<List<CustomerOrderDto>> orders(List<Customer> list){
//
//        System.out.println("Orders method invoked for" + list);
//        return this.orderService.ordersByCustomerName(list.stream().map(Customer::getName).toList());
//    }

    //N+1 problem solution
    @BatchMapping(typeName = "Customer")
    public Mono<Map<Customer, List<CustomerOrderDto>>> orders(List<Customer> list){

        System.out.println("Orders method invoked for" + list);
        return this.orderService.fetchOrdersAsMap(list);
    }

    @SchemaMapping(typeName = "Customer")
    public Mono<String> age(){
        return Mono.just("101");
    }
}
