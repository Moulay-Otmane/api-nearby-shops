package com.codingchallenge.api_nearby_shops.repository;

import com.codingchallenge.api_nearby_shops.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // This function search for user by email
    Optional<User> findByEmail(String email);

    // This function search for user by id
    Optional<User> findById(String id);
}
