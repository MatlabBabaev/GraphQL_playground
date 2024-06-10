package com.graphql.praphqlplay.lec09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class AllTypes {
    private UUID id;
    private Integer height;
    private Float temp;
    private String city;
    private Boolean isValid;
    private Long distance;
    private Byte ageIntYears;
    private Short ageMonth;
    private BigDecimal bigDecimal;
    private LocalDate date; //Here we have to use LoalDate as scalar user LocalDate
    private LocalTime time;
    private OffsetDateTime dateTime;
    private Car car;
}
