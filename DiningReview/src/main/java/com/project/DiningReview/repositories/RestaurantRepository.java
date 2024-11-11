package com.project.DiningReview.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.DiningReview.entities.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findRestaurantById(Long id);

    Optional<Restaurant> findRestaurantByNameAndZipCode(String name, String zipCode);

    List<Restaurant> findRestaurantByZipCodeAndEggScoreNotNullOrderByEggScore(String zipCode);
    List<Restaurant> findRestaurantByZipCodeAndDairyScoreNotNullOrderByDairyScore(String zipCode);
    List<Restaurant> findRestaurantByZipCodeAndPeanutScoreScoreNotNullOrderByPeanutScore(String zipCode);
}
