package com.example.taskmanager_backend.service.impl;

import com.example.taskmanager_backend.dto.request.SignUpRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.entities.User;
import com.example.taskmanager_backend.repository.UserRepository;
import com.example.taskmanager_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResponse signup(SignUpRequest signUpRequest) {

        if (userRepository.existsByName(signUpRequest.getName())) {
            throw new RuntimeException();
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException();
        }

        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());

        userRepository.save(user);
        return BaseResponse.getSuccessMessage();
    }






}
