package com.example.taskmanager_backend.service.impl;

import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.entities.User;
import com.example.taskmanager_backend.enums.Role;
import com.example.taskmanager_backend.exception.EmailAlreadyExist;
import com.example.taskmanager_backend.exception.WrongRoleName;
import com.example.taskmanager_backend.repository.UserRepository;
import com.example.taskmanager_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResponse create(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailAlreadyExist("The include email already exist.");
        }
        if (userRequest.getRole()!= Role.ADMIN){
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
