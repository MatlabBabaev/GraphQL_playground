package com.graphql.praphqlplay.lec02.entity;

import lombok.Data;

@Data
public class AgeRangeFilter {

    private Integer minAge;
    private Integer maxAge;
}
