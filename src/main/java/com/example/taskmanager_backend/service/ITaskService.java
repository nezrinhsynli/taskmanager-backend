package com.example.taskmanager_backend.service;

import com.example.taskmanager_backend.dto.request.TaskRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.dto.response.TaskResponse;

import java.util.List;

public interface ITaskService {

    BaseResponse create(TaskRequest taskRequest);

    List<TaskResponse> getAll();

    TaskResponse getById(Long id);

    String update(Long id, TaskRequest taskRequest);

    String delete(Long id);


}
