package com.splitar.repository;

import com.splitar.model.RidePost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RidePostRepository extends MongoRepository<RidePost, String> {
    List<RidePost> findByExpiredFalse();
    List<RidePost> findByDestinationContainingIgnoreCase(String destination);
}





