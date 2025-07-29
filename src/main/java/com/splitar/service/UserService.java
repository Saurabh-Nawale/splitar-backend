package com.splitar.service;

import com.splitar.model.User;
import com.splitar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save a new user or update if exists
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Get user by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(String id, User userUpdates) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    if (userUpdates.getMobile() != null) {
                        existingUser.setMobile(userUpdates.getMobile());
                    }
                    // ✅ ADDED: Logic to update the gender field
                    if (userUpdates.getGender() != null) {
                        existingUser.setGender(userUpdates.getGender());
                    }
                    return userRepository.save(existingUser);
                });
    }
}
