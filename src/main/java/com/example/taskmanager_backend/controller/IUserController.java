package com.example.taskmanager_backend.controller;

import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public interface IUserController {

    public BaseResponse create(UserRequest userRequest);
    BaseResponse getAllUsers();
    BaseResponse getUserById(@PathVariable Long id);


}
