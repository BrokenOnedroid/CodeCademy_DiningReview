package com.project.DiningReview.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.DiningReview.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findUserByDisplayName(String displayName); 
}
