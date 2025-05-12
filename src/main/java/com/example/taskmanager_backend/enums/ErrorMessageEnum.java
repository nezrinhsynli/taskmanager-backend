package com.example.taskmanager_backend.enums;

public enum ErrorMessageEnum {
    EMAIL_ALREADY_EXISTS("The included email already exists."),
    WRONG_ROLE("Only Admin can create Users."),
    ORGANIZATION_NOT_FOUND("The provided organization ID was not found."),
    USER_NOT_FOUND("User not found with given id."),
    ORGANIZATION_ALREADY_EXISTS("Organization name already exists.");

    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}