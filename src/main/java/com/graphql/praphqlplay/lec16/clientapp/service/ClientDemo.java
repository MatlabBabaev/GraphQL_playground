package com.graphql.praphqlplay.lec16.clientapp.service;

import com.graphql.praphqlplay.lec16.dto.CustomerDto;
import com.graphql.praphqlplay.lec16.clientapp.client.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class ClientDemo implements CommandLineRunner {

    @Autowired
    private CustomerClient client;

    @Override
    public void run(String... args) throws Exception {
        rawQueryDemo().subscribe();
    }

    private Mono<Void> rawQueryDemo(){
//        String query = "                {\n" +
//                "                    customers{\n" +
//                "                        id\n" +
//                "                        name\n" +
//                "                        age\n" +
//                "                        city\n" +
//                "                    }\n" +
//                "                }";
        // this is the same as above. In java17 it can be used
        String query = """               
                        {
                           a: customers{
                              id
                              name
                              age
                              city
                            }
                        }
                """;

        Mono<List<CustomerDto>> mono = this.client.rawQuery(query)
                //Here we are asking only for the field "customers"
                //And converting it to CustomerDto class
                .map(cr -> cr.field("a").toEntityList(CustomerDto.class));

        //We are calling the query to get all the customers: and waiting 1 second
        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(()-> System.out.println("RAW QUERY DEMO"))
                .then(mono)
                .doOnNext(System.out::println)
                .then();
    }
}