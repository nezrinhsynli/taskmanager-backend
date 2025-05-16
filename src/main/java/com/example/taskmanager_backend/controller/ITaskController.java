package com.example.taskmanager_backend.controller;

import com.example.taskmanager_backend.dto.request.TaskRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.dto.response.TaskResponse;

import java.util.List;

public interface ITaskController {

    BaseResponse create(TaskRequest taskRequest);

    List<TaskResponse> getAll();

}
