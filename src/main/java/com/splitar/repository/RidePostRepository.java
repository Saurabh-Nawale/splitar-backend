package com.splitar.repository;

import com.splitar.model.RidePost;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RidePostRepository extends MongoRepository<RidePost, String> {

    // Finds all non-expired rides.
    List<RidePost> findByIsExpiredFalse();

    // Finds rides by a specific user's ID.
    List<RidePost> findByUserId(String userId);

    // Finds rides where the destination contains the given string (case-insensitive).
    List<RidePost> findByDestinationContainingIgnoreCase(String destination);
}
