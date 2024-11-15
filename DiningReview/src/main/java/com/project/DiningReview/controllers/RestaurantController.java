package com.project.DiningReview.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.DiningReview.entities.Restaurant;
import com.project.DiningReview.repositories.RestaurantRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Optional<Restaurant> restaurantOptional;
        String restaurantOptionalName;
        String restaurantOptionalZipCode;

        restaurantOptionalName = restaurant.getName();
        restaurantOptionalZipCode = restaurant.getZipCode();

        restaurantOptional = restaurantRepository.findRestaurantByNameAndZipCode(restaurantOptionalName, restaurantOptionalZipCode);

        if (!restaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restaurant already exits.");
        }

        restaurantRepository.save(restaurant);
    }

    @GetMapping("/Search")
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> searchForRestaurants(@RequestParam String zipCode, @RequestParam String allergyType) {
        List<Restaurant> resultList;
        // Validation for zip?
        if (!validateZipCode(zipCode)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RequestParam (zipCode) not valid.");
        }

        if (allergyType == null || allergyType.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RequestParam (allergyType) missing or empty.");
        }

        if (allergyType.equalsIgnoreCase("dairy")) {
            resultList = restaurantRepository.findRestaurantByZipCodeAndDairyScoreNotNullOrderByDairyScore(zipCode);
        } else if (allergyType.equalsIgnoreCase("peanut")) {
            resultList = restaurantRepository.findRestaurantByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(zipCode);
        } else if (allergyType.equalsIgnoreCase("egg")) {
            resultList = restaurantRepository.findRestaurantByZipCodeAndEggScoreNotNullOrderByEggScore(zipCode);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unknow allergyType param.");
        }

        return resultList;
    }

    private boolean validateZipCode(String zipCode) {
        //see https://stackoverflow.com/questions/160550/zip-code-us-postal-code-validation
        Pattern pattern = Pattern.compile("^([0-9]{5})(?:[-\\s]*([0-9]{4}))?$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(zipCode);
        return matcher.matches();
    }

}
