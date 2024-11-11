package com.project.DiningReview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.DiningReview.entities.Review;
import com.project.DiningReview.entities.ReviewStatus;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByStatus(ReviewStatus reviewStatus);
    List<Review> findReviewsByRestaurantIdAndStatus(Long restaurantId, ReviewStatus reviewStatus);

}
