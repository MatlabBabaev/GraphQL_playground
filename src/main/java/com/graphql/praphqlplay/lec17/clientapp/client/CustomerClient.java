package com.graphql.praphqlplay.lec17.clientapp.client;

import com.graphql.praphqlplay.lec17.dto.CustomerDto;
import com.graphql.praphqlplay.lec17.dto.DeleteResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CustomerClient {

    private final HttpGraphQlClient client;

    public CustomerClient(@Value("${customer.service.url}") String baseUrl){
        this.client = HttpGraphQlClient.builder()
                .webClient(b -> b.baseUrl(baseUrl))
                .build();
    }

    public Mono<List<CustomerDto>> allCustomers(){
        return crud("GetAll", Collections.EMPTY_MAP, new ParameterizedTypeReference<List<CustomerDto>>(){});
    }

    public Mono<CustomerDto> getCustomerById(Integer id){
        return crud("GetCustomerById", Map.of("id", id), new ParameterizedTypeReference<CustomerDto>() {
        });
    }

    public Mono<CustomerDto> createCustomer(CustomerDto customerDto){
        return this.crud("CreateCustomer", Map.of("customer", customerDto), ParameterizedTypeReference.forType(CustomerDto.class));
    }

    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto customerDto){
        return this.crud("UpdateCustomer", Map.of("customer", customerDto, "id", id), ParameterizedTypeReference.forType(CustomerDto.class));
    }

    public Mono<CustomerDto> deleteCustomer(Integer id){
        return this.crud("DeleteCustomer", Map.of("id", id), ParameterizedTypeReference.forType(DeleteResponseDto.class));
    }

    private <T> Mono <T> crud(String operationName, Map<String, Object> variables, ParameterizedTypeReference<T> type){
        return this.client.documentName("crud-operations")
                .operationName(operationName)
                .variables(variables)
                .retrieve("response")
                .toEntity(type);
    }
}
