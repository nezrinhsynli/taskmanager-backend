package com.example.taskmanager_backend.dto.response;

import com.example.taskmanager_backend.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDate deadline;
    private TaskStatus status;
    private UserResponse userResponse;

}
