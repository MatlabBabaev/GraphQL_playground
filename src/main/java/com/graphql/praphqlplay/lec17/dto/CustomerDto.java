package com.graphql.praphqlplay.lec17.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@JsonInclude(JsonInclude.Include.NON_NULL) //this guy will send field that are not null
//if example, field id is null then it will send 3 fields
public class CustomerDto implements CustomerResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
