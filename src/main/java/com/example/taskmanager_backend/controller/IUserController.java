package com.example.taskmanager_backend.controller;

import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import org.springframework.stereotype.Controller;

@Controller
public interface IUserController {

    public BaseResponse create(UserRequest userRequest);


}
