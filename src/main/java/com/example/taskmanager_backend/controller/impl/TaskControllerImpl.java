package com.example.taskmanager_backend.controller.impl;

import com.example.taskmanager_backend.controller.ITaskController;
import com.example.taskmanager_backend.dto.request.TaskRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.dto.response.TaskResponse;
import com.example.taskmanager_backend.service.ITaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping(path = "/get-by-id/{id}")
    @Override
    public TaskResponse getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PutMapping(path = "/update/{id}")
    @Override
    public String update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @DeleteMapping(path = "/delete/{id}")
    @Override
    public String delete(@PathVariable Long id) {
        return taskService.delete(id);
    }

}
