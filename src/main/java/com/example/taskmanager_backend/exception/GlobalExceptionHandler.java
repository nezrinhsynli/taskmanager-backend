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
    public ErrorResponse<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errorMap = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMap.computeIfAbsent(fieldError.getField(), key -> new ArrayList<>()).add(fieldError.getDefaultMessage());
        }

        return new ErrorResponse<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.value(),
                errorMap
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse<String> handleUserNotFoundException(UserNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Email artıq varsa
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ErrorResponse<String> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Organization artıq varsa
    @ExceptionHandler(OrganizationAlreadyExistException.class)
    public ErrorResponse<String> handleOrganizationAlreadyExistException(OrganizationAlreadyExistException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // Organization tapılmayıbsa
    @ExceptionHandler(OrganizationNotFoundException.class)
    public ErrorResponse<String> handleOrganizationNotFoundException(OrganizationNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Yanlış rol daxil olunubsa
    @ExceptionHandler(WrongRoleNameException.class)
    public ErrorResponse<String> handleWrongRoleNameException(WrongRoleNameException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Ümumi resurs tapılmayanda
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Reusable metod: JSON formatında cavab qaytarır
    private ErrorResponse<String> buildErrorResponse(String message, HttpStatus status) {
        return new ErrorResponse<>(
                LocalDateTime.now(),
                status.value(),
                status.value(),
                message
        );
    }



}
