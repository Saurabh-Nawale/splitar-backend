package com.splitar.service;

import com.splitar.model.RidePost;
import com.splitar.repository.RidePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class RidePostService {

    private final RidePostRepository ridePostRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public RidePostService(RidePostRepository ridePostRepository, MongoTemplate mongoTemplate) {
        this.ridePostRepository = ridePostRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public RidePost createRidePost(RidePost ridePost) {
        return ridePostRepository.save(ridePost);
    }

    public List<RidePost> findRides(String userId) {
        if (userId != null && !userId.isEmpty()) {
            return ridePostRepository.findByUserId(userId);
        } else {
            return ridePostRepository.findByIsExpiredFalse();
        }
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
            return null; // Or throw an exception
        }
    }

    public List<RidePost> searchRides(String from, String to, String timeSlot, String genderPreference) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        criteriaList.add(Criteria.where("isExpired").is(false));

        if (from != null && !from.isEmpty()) {
            criteriaList.add(Criteria.where("currentLocation").regex(Pattern.quote(from), "i"));
        }
        if (to != null && !to.isEmpty()) {
            criteriaList.add(Criteria.where("destination").regex(Pattern.quote(to), "i"));
        }
        if (genderPreference != null && !genderPreference.isEmpty() && !genderPreference.equalsIgnoreCase("Any")) {
            criteriaList.add(Criteria.where("genderPreference").is(genderPreference));
        }

        LocalDate today = LocalDate.now();
        LocalDateTime timeFrom;
        LocalDateTime timeTo;

        if (timeSlot != null && !timeSlot.isEmpty()) {
            try {
                String[] times = timeSlot.split("-");
                timeFrom = LocalDateTime.of(today, LocalTime.parse(times[0]));
                timeTo = LocalDateTime.of(today, LocalTime.parse(times[1]));
            } catch (Exception e) {
                System.err.println("Could not parse time slot, falling back to default. Error: " + e.getMessage());
                timeFrom = LocalDateTime.now();
                timeTo = today.plusDays(1).atStartOfDay();
            }
        } else {
            timeFrom = LocalDateTime.now();
            timeTo = today.plusDays(1).atStartOfDay();
        }

        criteriaList.add(Criteria.where("datetime").gte(timeFrom).lt(timeTo));
        query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        return mongoTemplate.find(query, RidePost.class);
    }
}
