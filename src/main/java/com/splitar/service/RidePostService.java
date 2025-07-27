package com.splitar.service;

import com.splitar.model.RidePost;
import com.splitar.repository.RidePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RidePostService {

    @Autowired
    private RidePostRepository ridePostRepository;

    public RidePost createRidePost(RidePost ridePost) {
        return ridePostRepository.save(ridePost);
    }

    public List<RidePost> getAllActiveRidePosts() {
        return ridePostRepository.findByExpiredFalse();
    }

    public Optional<RidePost> getRidePostById(String id) {
        return ridePostRepository.findById(id);
    }

    public List<RidePost> getRidePostsByDestination(String destination) {
        return ridePostRepository.findByDestinationContainingIgnoreCase(destination);
    }

    public void deleteRidePost(String id) {
        ridePostRepository.deleteById(id);
    }

    public RidePost markAsExpired(String id) {
        Optional<RidePost> optionalRidePost = ridePostRepository.findById(id);
        if (optionalRidePost.isPresent()) {
            RidePost post = optionalRidePost.get();
            post.setExpired(true);
            return ridePostRepository.save(post);
        } else {
            return null;
        }
    }
}
