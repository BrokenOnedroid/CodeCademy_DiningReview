package com.project.DiningReview.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.DiningReview.entities.Restaurant;
import com.project.DiningReview.repositories.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/Restaurant")
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public void addRestaurant(@RequestBody Restaurant restaurant) {

    }

}
