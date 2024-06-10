package com.graphql.praphqlplay.lec05;

import com.graphql.praphqlplay.lec05.entity.Address;
import com.graphql.praphqlplay.lec05.entity.Customer;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

    @SchemaMapping(typeName = "Customer")
    public Mono<Address> address(Customer customer, DataFetchingEnvironment environment){
        System.out.println("address: " + environment.getDocument());
        return Mono.just(
                Address.create(customer.getName() + " street", customer.getCity() + " city" ));
    }
}
