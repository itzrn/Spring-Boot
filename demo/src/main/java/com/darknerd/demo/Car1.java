package com.darknerd.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Car1 {
    @Autowired
    private Car car; // this is a field

    @GetMapping("/ok")
    public String ok(){
        return car.fun();
    }
}
