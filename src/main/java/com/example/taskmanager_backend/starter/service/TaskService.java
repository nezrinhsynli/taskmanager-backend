package com.example.taskmanager_backend.starter.service;

import com.example.taskmanager_backend.starter.dao.entities.Task;
import com.example.taskmanager_backend.starter.dao.repositories.TaskRepository;
import com.example.taskmanager_backend.starter.dao.repositories.UserRepository;
import com.example.taskmanager_backend.starter.enums.TaskStatus;
import com.example.taskmanager_backend.starter.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found with id:"+id));

    }
    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDeadline(updatedTask.getDeadline());
        task.setStatus(updatedTask.getStatus());
        task.setAssiginedUsers(updatedTask.getAssiginedUsers());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task changeStatus(Long id, TaskStatus status) {
        Task task = getTaskById(id);
        task.setStatus(status);
        return taskRepository.save(task);
    }

}
