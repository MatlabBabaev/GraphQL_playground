package com.graphql.praphqlplay.lec11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class Electronics {
    private final UUID id = UUID.randomUUID();
    private String description;
    private Integer price;
    private String brand;
}
