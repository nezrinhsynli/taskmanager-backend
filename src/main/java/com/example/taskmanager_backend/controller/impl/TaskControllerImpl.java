package com.example.taskmanager_backend.controller.impl;

import com.example.taskmanager_backend.controller.ITaskController;
import com.example.taskmanager_backend.dto.request.TaskRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.dto.response.TaskResponse;
import com.example.taskmanager_backend.entities.Task;
import com.example.taskmanager_backend.service.ITaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "rest/api/task")
public class TaskControllerImpl implements ITaskController {

    private final ITaskService taskService;

    @PostMapping(path = "/create")
    @Override
    public BaseResponse create(@RequestBody @Valid TaskRequest taskRequest) {
        return taskService.create(taskRequest);
    }

    @GetMapping(path = "/get-all")
    @Override
    public List<TaskResponse> getAll() {
       return taskService.getAll();
    }


}
