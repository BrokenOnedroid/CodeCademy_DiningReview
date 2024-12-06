package com.project.DiningReview.controllers;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.project.DiningReview.repositories.RestaurantRepository;
import com.project.DiningReview.repositories.ReviewRepository;

import com.project.DiningReview.entities.ReviewStatus;
import com.project.DiningReview.entities.Review;
import com.project.DiningReview.entities.AdminReviewAction;
import com.project.DiningReview.entities.Restaurant;

import java.util.List;
import java.util.Optional;

@RequestMapping("/Admin")
@RestController
public class AdminController {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminController(final ReviewRepository reviewRepository, final RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status value not accepted.");
        }

        reviews = reviewRepository.findReviewsByStatus(reviewStatus);

        if (reviews.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "nothing found.");
        }
        return reviews;
    }

    @PutMapping("/reviews/{reviewId}")
    public void reviewAction(@PathVariable Long reviewId, @RequestBody AdminReviewAction adminReviewAction) {
        Optional<Review> optReview = reviewRepository.findById(reviewId);
        if (optReview.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Review found!");
        }

        Review review = optReview.get();

        Optional<Restaurant> optRestaurant = restaurantRepository.findById(review.getRestaurantId());
        if (optRestaurant.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Restaurant not found!");
        }

        //updatin status depending on Body Var
        if (adminReviewAction.getAccept()) {
            review.setStatus(ReviewStatus.ACCEPTED);
        } else {
            review.setStatus(ReviewStatus.REJECTED);
        }

        reviewRepository.save(review);
        updateReviewScore(optRestaurant.get());
    }

    private void updateReviewScore(Restaurant restaurant) {
        List<Review> reviews = reviewRepository.findReviewsByRestaurantIdAndStatus(restaurant.getId(), ReviewStatus.ACCEPTED);

        // vars for calc of average
        Integer peanutCount = 0;
        Integer diaryCount = 0;
        Integer eggCount = 0;
        
        Integer peanutSum = 0;
        Integer diarySum = 0;
        Integer eggSum = 0;

        // iter through reviews list for each loop
        for (Review r : reviews) {
            if (!ObjectUtils.isEmpty(r.getPeanutScore())) {
                peanutSum += r.getPeanutScore();
                peanutCount ++;
            }
            if (!ObjectUtils.isEmpty(r.getDairyScore())) {
                diarySum += r.getDairyScore();
                diaryCount ++;
            }
            if (!ObjectUtils.isEmpty(r.getEggScore())) {
                eggSum += r.getEggScore();
                eggCount ++;
            }
        }

        //compute average overallScore
        Integer totalScore = peanutSum + diarySum + eggSum;
        Integer totalCount = peanutCount + diaryCount + eggCount;

        float averageScore = totalScore / totalCount;
        // after https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
        restaurant.setOverallScore(String.format("%.2f", averageScore));

        // updating the single scores
        if (eggCount > 0) {
            float eggScore = eggSum / eggCount;
            restaurant.setEggScore(String.format("%.2f", eggScore));
        }

        if (peanutCount > 0) {
            float peanutScore = peanutSum / peanutCount;
            restaurant.setPeanutScore(String.format("%.2f", peanutScore));            
        }

        if (diaryCount > 0) {
            float dairyScore = diarySum / diaryCount;
            restaurant.setDairyScore(String.format("%.2f", dairyScore));
        }

        restaurantRepository.save(restaurant);
    }
}
