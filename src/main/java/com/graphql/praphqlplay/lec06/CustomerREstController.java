package com.graphql.praphqlplay.lec06;

import com.graphql.praphqlplay.lec06.entity.CustomerOrderDto;
import com.graphql.praphqlplay.lec06.entity.CustomerWithOrder;
import com.graphql.praphqlplay.lec06.service.CustomerService;
import com.graphql.praphqlplay.lec06.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class CustomerREstController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("customers")
    public Flux<CustomerWithOrder> customerOrders() {
        return this.customerService.allCustomers()
                .flatMap(c ->
                        this.orderService.ordersByCustomerName(c.getName())
                                .collectList()
                                .map(l -> CustomerWithOrder.create(c.getId(), c.getName(), c.getAge(), c.getCity(), l))
                );
    }
}
