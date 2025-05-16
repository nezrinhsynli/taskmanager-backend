package com.example.taskmanager_backend.exception;

public class WrongRoleNameException extends RuntimeException {
    public WrongRoleNameException(String message) {
        super(message);
    }
}
