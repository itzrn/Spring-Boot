package com.spring.learning.controller;


import com.spring.learning.entity.User;
import com.spring.learning.repository.UserRepository;
import com.spring.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    // this we will create for admin
//    @GetMapping
//    public List<User> getAllUser(){
//        return userService.getAll();
//    }

    // putted in the PublicController as this function is need to be public
//    @PostMapping
//    public void createUser(@RequestBody User user){
//        userService.saveNewUser(user);
//    }


    // this was when there was no spring security
//    @PutMapping("/{userName}")
//    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
//        User userInDb = userService.findByUserName(userName);
//        if(userInDb != null){
//            userInDb.setUserName(user.getUserName());
//            userInDb.setPassword(user.getPassword());
//            userService.saveEntry(userInDb);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
    // now to use this particular api u need to authenticate it with userName and password so from there we can access the userName
    // so where this userName will get hold on, it's in SecurityContextHolder
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findUserByUserName(userName);
//        if(userInDb != null){
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
//        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-by-username")
    public ResponseEntity<?> deleteUserByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
