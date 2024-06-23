package com.graphql.praphqlplay.lec17.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerNotFound implements CustomerResponse {
    private Integer id;
    private final String message = "user not found";
}
