package com.example.taskmanager_backend.service;

import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;


public interface IUserService {

    BaseResponse create(UserRequest userRequest);
    BaseResponse getAllUsers();
    BaseResponse getUserById(Long id);
    BaseResponse updateUser(Long id, UserRequest userRequest);
    BaseResponse deleteUser(Long id);

}

