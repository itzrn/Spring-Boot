package com.springpractice.projectone;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@RequestMapping("/my")
public class Teacher {

    @Autowired
    Student student;

    // to use the api we use GetMapping annotation
    @GetMapping("/hello")
    public String greetings() {
        return student.hello();
    }
    
    @GetMapping("/api")
    public String api(){
        return "I am your First API Teacher";
    }
    

}
