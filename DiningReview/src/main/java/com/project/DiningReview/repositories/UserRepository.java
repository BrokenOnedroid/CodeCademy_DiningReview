package com.project.DiningReview.repositories;

import java.util.Optional; 

import org.springframework.data.repository.CrudRepository;

import com.project.DiningReview.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
   Optional<User> findUserByDisplayName(String displayName); 
}
