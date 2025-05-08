package com.example.taskmanager_backend.exception;

public class WrongRoleName extends RuntimeException {

    public WrongRoleName(String message) {
        super(message);
    }
}
