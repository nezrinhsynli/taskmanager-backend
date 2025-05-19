package com.example.taskmanager_backend.service.impl;

import com.example.taskmanager_backend.dto.request.TaskRequest;
import com.example.taskmanager_backend.dto.response.BaseResponse;
import com.example.taskmanager_backend.dto.response.TaskResponse;
import com.example.taskmanager_backend.dto.response.UserResponse;
import com.example.taskmanager_backend.entities.Task;
import com.example.taskmanager_backend.entities.User;
import com.example.taskmanager_backend.enums.ErrorMessageEnum;
import com.example.taskmanager_backend.enums.Role;
import com.example.taskmanager_backend.exception.TaskNotFoundException;
import com.example.taskmanager_backend.exception.UserNotFoundException;
import com.example.taskmanager_backend.exception.WrongRoleNameException;
import com.example.taskmanager_backend.repository.TaskRepository;
import com.example.taskmanager_backend.repository.UserRepository;
import com.example.taskmanager_backend.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public BaseResponse create(TaskRequest taskRequest) {
        Task task = new Task();

        if (taskRequest.getRole() == Role.Admin) {
            BeanUtils.copyProperties(taskRequest, task);

            Optional<User> optionalUser = userRepository.findById(taskRequest.getUserId());
            if (optionalUser.isEmpty()) {
                throw new UserNotFoundException(ErrorMessageEnum.USER_NOT_FOUND.getMessage());
            }
            task.setUser(optionalUser.get());
            taskRepository.save(task);
            return BaseResponse.success("Task successfully created");
        }

        if (taskRequest.getRole() == Role.User && taskRequest.getCreatorId().equals(taskRequest.getUserId())) {
            BeanUtils.copyProperties(taskRequest, task);

            Optional<User> optionalUser = userRepository.findById(taskRequest.getUserId());
            if (optionalUser.isEmpty()) {
                throw new UserNotFoundException(ErrorMessageEnum.USER_NOT_FOUND.getMessage());
            }
            task.setUser(optionalUser.get());
            taskRepository.save(task);
            return BaseResponse.success("Task successfully created");
        } else {
            throw new WrongRoleNameException("USER can only assign tasks to themselves!");
        }
    }

    @Override
    public List<TaskResponse> getAll() {
        List<Task> taskList = taskRepository.findAll();
        if (taskList.isEmpty()) {
            throw new TaskNotFoundException(ErrorMessageEnum.TASK_NOT_FOUND.getMessage());
        }

        List<TaskResponse> taskResponseList = new ArrayList<>();

        for (Task task : taskList) {
            User user = task.getUser();
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);

            TaskResponse taskResponse = new TaskResponse();
            BeanUtils.copyProperties(task, taskResponse);
            taskResponse.setUserResponse(userResponse);

            taskResponseList.add(taskResponse);
        }
        return taskResponseList;
    }

    @Override
    public TaskResponse getById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException(ErrorMessageEnum.TASK_NOT_FOUND.getMessage());
        }

        Task task = optionalTask.get();
        TaskResponse taskResponse = new TaskResponse();
        BeanUtils.copyProperties(task, taskResponse);

        if (task.getUser() == null) {
            throw new NullPointerException(ErrorMessageEnum.USER_NOT_FOUND.getMessage());
        }

        User user = task.getUser();
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);

        taskResponse.setUserResponse(userResponse);
        return taskResponse;
    }

    @Override
    public String update(Long id, TaskRequest taskRequest) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException(ErrorMessageEnum.TASK_NOT_FOUND.getMessage());
        }

        Task task = optionalTask.get();
        BeanUtils.copyProperties(taskRequest, task);

        Optional<User> optionalUser = userRepository.findById(taskRequest.getUserId());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(ErrorMessageEnum.USER_NOT_FOUND.getMessage());
        }

        task.setUser(optionalUser.get());
        taskRepository.save(task);

        return "Process success compiled";
    }

    @Override
    public String delete(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException(ErrorMessageEnum.TASK_NOT_FOUND.getMessage());
        }
        taskRepository.delete(optionalTask.get());
        return "Process success compiled";
    }


}
