package com.example.taskmanager_backend.service;

import com.example.taskmanager_backend.dto.request.SignUpRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;

public interface IUserService {

    BaseResponse signup(SignUpRequest signUpRequest);

}
