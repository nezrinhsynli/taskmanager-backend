package com.example.taskmanager_backend.starter.service;

import com.example.taskmanager_backend.starter.dao.entities.User;
import com.example.taskmanager_backend.starter.dao.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User updateUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            user.setPhone(updateUser.getPhone());
            user.setPassword(updateUser.getPassword());
            return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
