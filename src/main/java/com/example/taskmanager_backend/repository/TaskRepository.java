package com.example.taskmanager_backend.repository;

import com.example.taskmanager_backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
