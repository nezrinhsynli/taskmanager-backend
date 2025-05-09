package com.example.taskmanager_backend.exception;

import com.example.taskmanager_backend.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errorMap = new HashMap<>();

        for (ObjectError objectError : ex.getAllErrors()) {
            FieldError fieldError = (FieldError) objectError;
            if (!errorMap.containsKey(fieldError.getField())) {
                errorMap.put(fieldError.getField(), listOfErrors(new ArrayList<>(), objectError.getDefaultMessage()));
            } else {
                errorMap.put(fieldError.getField(), listOfErrors(errorMap.get(fieldError.getField()), objectError.getDefaultMessage()));
            }
        }

        ErrorResponse<Map<String, List<String>>> errorResponse = new ErrorResponse<>();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(errorMap);

        return errorResponse;
    }

    private List<String> listOfErrors(List<String> list, String message) {
        list.add(message);
        return list;
    }
    public ErrorResponse<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());

        return errorResponse;
    }
}
