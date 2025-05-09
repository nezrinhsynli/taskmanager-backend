package com.example.taskmanager_backend.service.impl;

import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.entities.User;
import com.example.taskmanager_backend.enums.Role;
import com.example.taskmanager_backend.exception.EmailAlreadyExist;
import com.example.taskmanager_backend.exception.ResourceNotFoundException;
import com.example.taskmanager_backend.exception.WrongRoleName;
import com.example.taskmanager_backend.repository.UserRepository;
import com.example.taskmanager_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        BaseResponse response = new BaseResponse();
        response.setMessage("User successfully fetched");
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setData(user);
        return response;
    }

    @Override
    public BaseResponse getAllUsers() {
        List<User> users = userRepository.findAll();
        BaseResponse response = new BaseResponse();
        response.setMessage("Users successfully fetched");
        response.setSuccess(true);
        response.setData(users);
        return response;
    }

    @Override
    public BaseResponse create(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailAlreadyExist("The include email already exist.");
        }
        if (userRequest.getRole() != Role.ADMIN) {
            throw new WrongRoleName("Only Admin can create Users.");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());

        userRepository.save(user);
        return BaseResponse.getSuccessMessage();
    }


}
