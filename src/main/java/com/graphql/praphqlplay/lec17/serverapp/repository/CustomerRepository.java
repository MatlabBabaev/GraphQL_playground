package com.graphql.praphqlplay.lec17.serverapp.repository;

import com.graphql.praphqlplay.lec17.serverapp.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
