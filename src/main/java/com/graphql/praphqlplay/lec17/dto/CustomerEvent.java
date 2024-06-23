package com.graphql.praphqlplay.lec17.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerEvent {

    private Integer id;
    private Action action;
}
