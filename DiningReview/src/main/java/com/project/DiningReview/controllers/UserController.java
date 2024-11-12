package com.project.DiningReview.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.DiningReview.entities.User;
import com.project.DiningReview.repositories.UserRepository;

import java.util.Optional;
import java.util.List;

@RequestMapping("/User")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //create Entry in db
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        //check input 
        User userToSave = checkForUser(user);
        //save ifokay
        userRepository.save(userToSave);
    }

    public User checkForUser(User user) {
        Optional<User> userOptional;

        // check if user var is valid (=> atleast contains displayname else query not possible)
        // not sure but isEmpty needs the display name to exits == null should catch that case
        if (user.getDisplayName() == null || user.getDisplayName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Displayname is necessary");
        }

        userOptional = userRepository.findUserByDisplayName(user.getDisplayName());
        // User already exits
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exits!");
        }
        
        return user;
    }
}