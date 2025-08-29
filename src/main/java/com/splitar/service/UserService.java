package com.splitar.service;

import com.splitar.model.User;
import com.splitar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> updateUserByEmail(String email, User userUpdates) {
        return userRepository.findByEmail(email)
                .map(existingUser -> {
                    if (userUpdates.getName() != null) {
                        existingUser.setName(userUpdates.getName());
                    }
                    if (userUpdates.getMobile() != null) {
                        existingUser.setMobile(userUpdates.getMobile());
                    }
                    if (userUpdates.getGender() != null) {
                        existingUser.setGender(userUpdates.getGender());
                    }
                    return userRepository.save(existingUser);
                });
    }

    public Optional<User> updateProfileImage(String email, MultipartFile file) throws IOException {
        return userRepository.findByEmail(email)
                .map(existingUser -> {
                    try {
                        String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
                        existingUser.setAvatarUrl("data:image/png;base64," + base64Image);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return userRepository.save(existingUser);
                });
    }
}
