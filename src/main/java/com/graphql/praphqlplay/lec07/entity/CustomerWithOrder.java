package com.graphql.praphqlplay.lec07.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class CustomerWithOrder {
    private Integer id;
    private String name;
    private Integer age;
    private String city;
    private List<CustomerOrderDto> orders;
}
