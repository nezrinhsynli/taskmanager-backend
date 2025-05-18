package com.example.taskmanager_backend.controller;

import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserController {

    public BaseResponse create(UserRequest userRequest);
    BaseResponse getAllUsers();
    BaseResponse getUserById( Long id);
    BaseResponse updateUser(Long id, UserRequest userRequest);
    BaseResponse deleteUser(Long id);

}
