package com.graphql.praphqlplay.lec09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class Product {

    private String name;
    private Map<String, String> attributes;
}
