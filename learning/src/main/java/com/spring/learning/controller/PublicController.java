package com.spring.learning.controller;

import com.spring.learning.entity.User;
import com.spring.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/")
public class PublicController { // in this class, all the function which returns something will automatically get convert into JSON and get send as response

    @Autowired
    private UserService userService;

    @GetMapping("health-check") // making this function with a path
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @GetMapping("user-by-username/{userName}")
    public User getUserBuUserName(@PathVariable String userName){
        return userService.findUserByUserName(userName);
    }
}
// making this controller is a good practice for every spring boot project
