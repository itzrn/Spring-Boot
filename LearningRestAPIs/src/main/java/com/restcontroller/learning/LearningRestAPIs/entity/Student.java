package com.restcontroller.learning.LearningRestAPIs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

// @Entity automatically make a table with the name as of class name
@Entity
@Getter
@Setter
public class Student {

    @Id // -> makes this attribute a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // -> use to generate the id unique
    private Long id;

    private String name;
    private String email;
}
// This is a way to map the object with the relation in database
// this concept is called as ORM(Object Relational Mapping)