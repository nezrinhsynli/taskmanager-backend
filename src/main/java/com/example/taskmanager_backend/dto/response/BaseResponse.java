package com.example.taskmanager_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {

    private int code;
    private String message;
    private LocalDateTime timestamp;

    public static BaseResponse getSuccessMessage() {
        return BaseResponse
                .builder()
                .code(0)
                .timestamp(LocalDateTime.now())
                .message("Process success compiled")
                .build();
    }

}
