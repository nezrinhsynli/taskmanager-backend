package com.example.taskmanager_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse<T> {

    private LocalDateTime timestamp;
    private Integer status;
    private Integer errorCode;
    private T message;

    public static <T> ErrorResponse<T> of(int status, T message) { //GlobalException ucun
        return new ErrorResponse<>(
                LocalDateTime.now(),
                status,
                status,
                message
        );

    }
}
