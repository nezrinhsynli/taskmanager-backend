package com.example.taskmanager_backend.starter.dao.repositories;

import com.example.taskmanager_backend.starter.dao.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
