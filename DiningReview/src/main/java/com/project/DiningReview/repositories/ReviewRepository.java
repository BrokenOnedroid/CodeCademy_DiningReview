package com.project.DiningReview.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.DiningReview.entities.Review;

import java.util.List;;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findReviewsByStatus(boolean status);
    List<Review> findReviewsByRestaurantIdAndStatus(Long restaurantId, boolean status);

}
