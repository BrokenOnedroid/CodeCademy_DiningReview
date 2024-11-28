package com.project.DiningReview.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.DiningReview.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
   Optional<AppUser> findUserByDisplayName(String displayName); 
}
