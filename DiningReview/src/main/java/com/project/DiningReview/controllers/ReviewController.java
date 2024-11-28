package com.project.DiningReview.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.DiningReview.entities.Restaurant;
import com.project.DiningReview.entities.Review;
import com.project.DiningReview.entities.ReviewStatus;
import com.project.DiningReview.entities.AppUser;

import com.project.DiningReview.repositories.RestaurantRepository;
import com.project.DiningReview.repositories.ReviewRepository;
import com.project.DiningReview.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/Review")
@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public ReviewController(final ReviewRepository reviewRepository, final RestaurantRepository restaurantRepository, final UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    
    @GetMapping("/Status")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewByStatus(@PathVariable ReviewStatus status) {
        return reviewRepository.findReviewsByStatus(status);
    }

    //findReviewsByRestaurantIdAndStatus

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addReview(@RequestBody Review review) {
        //Validate Review
        validateReviewData(review);

        // check for restaurant
        Optional<Restaurant> optRestaurant = restaurantRepository.findById(review.getRestaurantId());
        if (!optRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaturant ID not found!");
        }

        //save if okay
        review.setStatus(ReviewStatus.PENDING);
        reviewRepository.save(review);
    }

    private void validateReviewData(final Review review) {

        if (review.getUserName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Username given!");
        }

       Optional<AppUser> optUser = userRepository.findUserByDisplayName(review.getUserName());
       if (optUser.isEmpty()) {
           throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User not known!");
       }

        if (ObjectUtils.isEmpty(review.getDairyScore()) ||  
            ObjectUtils.isEmpty(review.getEggScore()) ||
            ObjectUtils.isEmpty(review.getPeanutScore())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing score values!");
        }

    } 

}