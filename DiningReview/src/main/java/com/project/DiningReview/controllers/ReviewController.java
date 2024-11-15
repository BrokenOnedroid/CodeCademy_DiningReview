package com.project.DiningReview.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.DiningReview.entities.Review;
import com.project.DiningReview.entities.ReviewStatus;
import com.project.DiningReview.repositories.ReviewRepository;

import java.util.List;

@RequestMapping("/Review")
@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(final ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
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

    }

}