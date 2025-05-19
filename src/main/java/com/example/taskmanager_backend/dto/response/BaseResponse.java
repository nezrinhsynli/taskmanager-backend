package com.example.taskmanager_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private String message;
    private LocalDateTime timestamp;
    private boolean success;
    private T data;


    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(
                message,
                LocalDateTime.now(),
                true,
                data
        );
    }

    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<>(
                message,
                LocalDateTime.now(),
                true,
                null
        );
    }
}


//    private String message;
//    private LocalDateTime timestamp;
//    private boolean success;
//    private T data;
//
//    // Success cavab - datasız
//    public static <T> BaseResponse<T> success(String message) {
//        return new BaseResponse<>(message, LocalDateTime.now(), true, null);
//    }
//
//    // Success cavab - datalı
//    public static <T> BaseResponse<T> success(String message, T data) {
//        return new BaseResponse<>(message, LocalDateTime.now(), true, data);
//    }
//
//    // Fail cavab
//    public static <T> BaseResponse<T> fail(String message) {
//        return new BaseResponse<>(message, LocalDateTime.now(), false, null);
//    }
//
//}