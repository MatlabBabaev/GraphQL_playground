package com.graphql.praphqlplay.lec04.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Customer {

    private Integer id;
    private String name;
    private Integer age;
    private String city;
}

//(staticName = "create"): This part of the annotation specifies that a static factory method named create
// should be generated along with the constructor. The staticName attribute allows you to specify
// the name of the static factory method.
//
//So, with @AllArgsConstructor(staticName = "create"), the generated code includes not only the constructor
// but also a static factory method like this:
//
//java
//public static Customer create(Integer id, String name, Integer age, String city) {
//    return new Customer(id, name, age, city);
//}
