package com.graphql.praphqlplay.lec16.clientapp.client;

import com.graphql.praphqlplay.lec16.dto.CustomerDto;
import com.graphql.praphqlplay.lec16.dto.CustomerNotFound;
import com.graphql.praphqlplay.lec16.dto.CustomerResponse;
import com.graphql.praphqlplay.lec16.dto.GenericResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerClient {

    private final HttpGraphQlClient client;

    public CustomerClient(@Value("${customer.service.url}") String baseUrl){
        this.client = HttpGraphQlClient.builder()
                .webClient(b -> b.baseUrl(baseUrl))
                .build();
    }

    public Mono<ClientGraphQlResponse> rawQuery(String query){
        return this.client.document(query)
                .execute();
    }

    public Mono<GenericResponse<CustomerDto>> getCustomerById(Integer id){

        return this.client.documentName("customer-by-id")
                .variable("id", id)
                .execute()
                .map(cr ->{
                 var field = cr.field("customerById");
                 return field.hasValue() ? new GenericResponse<>(field.toEntity(CustomerDto.class)) :
                         new GenericResponse<>(field.getError());
                });
    }

    public Mono<CustomerResponse> getCustomerByIdWithUnion(Integer id){

        return this.client.documentName("customer-by-id")
                .variable("id", id)
                .execute()
                .map(cr ->{

                    var field = cr.field("customerById");

                    //here we are checking what is the type
                    var isCustomer = "Customer".equals(cr.field("customerById.type").getValue().toString());

                    return isCustomer ? field.toEntity(CustomerDto.class) : field.toEntity(CustomerNotFound.class);
                });
    }

    //Using the document under resources
//    public Mono<CustomerDto> getCustomerById(Integer id){
//
////        here we can refer the graphql document to query
//        return this.client.documentName("customer-by-id")
//                .variable("id", id)
//                .retrieve("customerById")
//                .toEntity(CustomerDto.class);
//    }

    //1. Assignment using the retrieve method
//    public Mono<MultiCustomerAssignment> getCustomerById(Integer id){
//
//        //Assignment: using the retrieve method
//        return this.client.documentName("customer-by-id")
//                .variable("id", id)
//                .retrieve("")// gives the whole object
//                .toEntity(MultiCustomerAssignment.class);
//    }

    //2. Assignment using the execute method
//    public Mono<MultiCustomerAssignment> getCustomerById(Integer id){
//
//        //Assignment: using the execute method
//        return this.client.documentName("customer-by-id")
//                .variable("id", id)
//                .execute()
//                .map(cr -> cr.toEntity(MultiCustomerAssignment.class));
//    }
}
