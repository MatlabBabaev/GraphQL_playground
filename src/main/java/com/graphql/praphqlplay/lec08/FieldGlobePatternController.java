package com.graphql.praphqlplay.lec08;

import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FieldGlobePatternController {

    @QueryMapping
    public Object level1(DataFetchingFieldSelectionSet selectionSet){
        System.out.println(selectionSet.contains("level2"));
        System.out.println(selectionSet.contains("level2/level3")); //true
        System.out.println(selectionSet.contains("**/level3"));  //true
        System.out.println(selectionSet.contains("level2/*/level5"));  //false
        System.out.println(selectionSet.contains("level2/**/level5"));  //true
        return null;
    }
}
