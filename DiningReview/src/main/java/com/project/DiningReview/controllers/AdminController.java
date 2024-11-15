package com.project.DiningReview.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.DiningReview.entities.ReviewStatus;
import com.project.DiningReview.repositories.RestaurantRepository;
import com.project.DiningReview.repositories.ReviewRepository;
import com.project.DiningReview.repositories.UserRepository;
import com.project.DiningReview.entities.Review;

import java.util.List;

@RequestMapping("/Admin")
@RestController
public class AdminController {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public AdminController(final ReviewRepository reviewRepository, final RestaurantRepository restaurantRepository, final UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    } 
    
    @GetMapping("/reviews/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getPendingReviews(@PathVariable String status) {
        List<Review> reviews;
        ReviewStatus reviewStatus;

        if (status.equalsIgnoreCase("PENDING")) {
            reviewStatus = ReviewStatus.PENDING;
        } else if (status.equalsIgnoreCase("REJECTED")) {
            reviewStatus = ReviewStatus.REJECTED;
        } else if (status.equalsIgnoreCase("ACCEPTED")) {
            reviewStatus = ReviewStatus.ACCEPTED;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status value not accepted.");
        }

        reviews = reviewRepository.findReviewsByStatus(reviewStatus);
        return reviews;
    }
}
