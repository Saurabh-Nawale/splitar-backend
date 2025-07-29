package com.splitar.controller;

import com.splitar.model.RidePost;
import com.splitar.service.RidePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@CrossOrigin(origins = "http://localhost:3000")
public class RidePostController {

    private final RidePostService ridePostService;

    @Autowired
    public RidePostController(RidePostService ridePostService) {
        this.ridePostService = ridePostService;
    }

    @PostMapping
    public ResponseEntity<RidePost> createRidePost(@RequestBody RidePost ridePost) {
        RidePost createdPost = ridePostService.createRidePost(ridePost);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RidePost>> getRides(@RequestParam(required = false) String userId) {
        List<RidePost> rides = ridePostService.findRides(userId);
        return ResponseEntity.ok(rides);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RidePost>> searchRides(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String timeSlot,
            @RequestParam(required = false) String genderPreference) {
        List<RidePost> rides = ridePostService.searchRides(from, to, timeSlot, genderPreference);
        return ResponseEntity.ok(rides);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RidePost> getRidePostById(@PathVariable String id) {
        return ridePostService.getRidePostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/expire")
    public ResponseEntity<RidePost> markAsExpired(@PathVariable String id) {
        RidePost expiredPost = ridePostService.markAsExpired(id);
        if (expiredPost != null) {
            return ResponseEntity.ok(expiredPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRidePost(@PathVariable String id) {
        ridePostService.deleteRidePost(id);
        return ResponseEntity.noContent().build();
    }
}
