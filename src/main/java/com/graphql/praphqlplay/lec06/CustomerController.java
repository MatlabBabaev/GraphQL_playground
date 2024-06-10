package com.graphql.praphqlplay.lec06;

import com.graphql.praphqlplay.lec06.entity.Customer;
import com.graphql.praphqlplay.lec06.entity.CustomerOrderDto;
import com.graphql.praphqlplay.lec06.entity.CustomerWithOrder;
import com.graphql.praphqlplay.lec06.service.CustomerOrderDataFetcher;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerOrderDataFetcher dataFetcher;

    @SchemaMapping(typeName = "Query") // This is the same as QueryMapping
    public Flux<CustomerWithOrder> customers(DataFetchingFieldSelectionSet selectionSet){
        return this.dataFetcher.customerOrders(selectionSet);
    }

//    @SchemaMapping(typeName = "Customer") // this is something like CustomerMapping
//    public Flux<CustomerOrderDto> orders(Customer customer){
//        return this.orderService.ordersByCustomerName(customer.getName());
//    }


}
