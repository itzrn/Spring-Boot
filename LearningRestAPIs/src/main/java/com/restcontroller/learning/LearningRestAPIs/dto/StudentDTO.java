package com.restcontroller.learning.LearningRestAPIs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String email;

    // we need to make getter setters constructor
    // instead we can use annotation @Data
    // @Data automatically makes the getter, setter, hash function, equals etc

    //@AllArgsConstructor -> makes the constructor which have all the fields as parameter
    // @NoArgsConstructor -> makes a constructor with no arguments
}
