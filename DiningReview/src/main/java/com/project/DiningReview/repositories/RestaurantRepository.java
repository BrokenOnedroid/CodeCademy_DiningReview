package com.project.DiningReview.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.DiningReview.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findRestaurantById(Long id);

    Optional<Restaurant> findRestaurantByNameAndZipCode(String name, String zipCode);

    List<Restaurant> findRestaurantByZipCodeAndEggScoreNotNullOrderByEggScore(String zipCode);
    List<Restaurant> findRestaurantByZipCodeAndDairyScoreNotNullOrderByDairyScore(String zipCode);
    List<Restaurant> findRestaurantByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(String zipCode);
}
