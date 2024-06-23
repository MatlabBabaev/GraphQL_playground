package com.graphql.praphqlplay.lec16.dto;

import lombok.Data;

@Data
public class CustomerDto implements CustomerResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
