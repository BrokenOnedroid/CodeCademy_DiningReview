package com.project.DiningReview.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.DiningReview.entities.User;
import com.project.DiningReview.repositories.UserRepository;

@RequestMapping("/User")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        //check input 
        checkForUser(user);
        //save ifokay
        userRepository.save(user);
    }

    public void checkForUser(User user) {
        
    }
}