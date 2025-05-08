package com.example.taskmanager_backend.controller.impl;

import com.example.taskmanager_backend.controller.IUserController;
import com.example.taskmanager_backend.dto.request.UserRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "rest/api/user")
public class UserControllerImpl implements IUserController {

    @Autowired
    IUserService userService;

    @PostMapping(path = "/create")
    @Override
    public BaseResponse create(@RequestBody @Valid UserRequest userRequest) {
       return userService.create(userRequest);
    }




}
