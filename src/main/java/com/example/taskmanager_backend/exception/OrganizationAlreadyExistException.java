package com.example.taskmanager_backend.exception;

public class OrganizationAlreadyExistException extends RuntimeException {
    public OrganizationAlreadyExistException(String message) {
        super(message);
    }
}
