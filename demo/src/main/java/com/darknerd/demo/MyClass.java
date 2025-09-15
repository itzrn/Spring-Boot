package com.darknerd.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//this particular annotation need to be provided to create an api end point
@RestController
public class MyClass {

    @GetMapping("helloIshika")
    public String sayHello(){
        return "Hello Ishika!";
    }
}



