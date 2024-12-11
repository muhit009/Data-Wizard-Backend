package org.example.userapi.service;

import org.example.userapi.entity.User;
import org.example.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        throw new IllegalArgumentException("Invalid username or password!");
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }
}