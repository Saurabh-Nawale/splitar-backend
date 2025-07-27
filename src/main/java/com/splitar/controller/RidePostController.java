package com.splitar.controller;

import com.splitar.model.RidePost;
import com.splitar.service.RidePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rideposts")
@CrossOrigin(origins = "*")
public class RidePostController {

    @Autowired
    private RidePostService ridePostService;

    @PostMapping
    public RidePost createRidePost(@RequestBody RidePost ridePost) {
        return ridePostService.createRidePost(ridePost);
    }

    @GetMapping
    public List<RidePost> getAllActiveRidePosts() {
        return ridePostService.getAllActiveRidePosts();
    }

    @GetMapping("/{id}")
    public Optional<RidePost> getRidePostById(@PathVariable String id) {
        return ridePostService.getRidePostById(id);
    }

    @GetMapping("/destination/{destination}")
    public List<RidePost> getRidePostsByDestination(@PathVariable String destination) {
        return ridePostService.getRidePostsByDestination(destination);
    }

    @DeleteMapping("/{id}")
    public void deleteRidePost(@PathVariable String id) {
        ridePostService.deleteRidePost(id);
    }

    @PutMapping("/{id}/expire")
    public RidePost markAsExpired(@PathVariable String id) {
        return ridePostService.markAsExpired(id);
    }
}
