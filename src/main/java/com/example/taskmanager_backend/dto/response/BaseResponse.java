package com.example.taskmanager_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private String message;
    private LocalDateTime timestamp;
    private boolean success;
    private Object data;

    public static BaseResponse getSuccessMessage() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Process success compiled");
        baseResponse.setTimestamp(LocalDateTime.now());
        baseResponse.setSuccess(true);
        return baseResponse;
    }

}
