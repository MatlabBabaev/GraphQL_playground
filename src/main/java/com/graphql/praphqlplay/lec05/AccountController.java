package com.graphql.praphqlplay.lec05;

import com.graphql.praphqlplay.lec05.entity.Account;
import com.graphql.praphqlplay.lec05.entity.Customer;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AccountController {

    @SchemaMapping(typeName = "Customer")
    public Mono<Account> account (Customer customer, DataFetchingFieldSelectionSet selectionSet){
        System.out.println("account: " + selectionSet.getFields());
        var type = ThreadLocalRandom.current().nextBoolean()? "CHECKING": "SAVING";
        return Mono.just(
                Account.create(UUID.randomUUID(),
                        ThreadLocalRandom.current().nextInt(1, 1000),
                        type)
        );
    }
}
