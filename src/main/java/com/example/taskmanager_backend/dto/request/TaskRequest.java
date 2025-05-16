package com.example.taskmanager_backend.dto.request;

import com.example.taskmanager_backend.enums.Role;
import com.example.taskmanager_backend.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    @Enumerated(EnumType.STRING)
    @NotNull(message = "The Role cannot be empty!")
    private Role role;

    private Long creatorId;

    @NotNull(message = "Title cannot be empty!")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters!")
    private String title;

    private String description;

    @FutureOrPresent(message = "Deadline must be today or a future date!")
    private LocalDate deadline;

    @NotNull(message = "Task status cannot be empty!")
    private TaskStatus status;

    @Positive(message = "ID must be a positive number!")
    private Long userId;


}
