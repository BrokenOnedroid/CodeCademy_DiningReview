//User entity-related scenarios:

//    As an unregistered user, I want to create my user profile using a display name that’s unique only to me.
//    As a registered user, I want to update my user profile. I cannot modify my unique display name.
//    As an application experience, I want to fetch the user profile belonging to a given display name.
//    As part of the backend process that validates a user-submitted dining review, I want to verify that the user exists, based on the user display name associated with the dining review.

package com.example.diningreview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;

import com.example.diningreview.models.User;

@RestController
public class UserController {
    @GetMapping("/{displayName}")
    public User getByName(@RequestParam(name="dispayName") String name) {
        return getByName(name);
    }

}