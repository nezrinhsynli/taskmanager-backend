package com.example.taskmanager_backend.exception;

public class OrganizationAlreadyExist extends RuntimeException {

    public OrganizationAlreadyExist(String message) {
        super(message);
    }
}
