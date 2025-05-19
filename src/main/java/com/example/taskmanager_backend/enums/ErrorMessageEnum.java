package com.example.taskmanager_backend.enums;

import lombok.Getter;

@Getter
public enum ErrorMessageEnum {
    EMAIL_ALREADY_EXISTS("The included email already exists."),
    WRONG_ROLE("Only Admin can create Users."),
    ORGANIZATION_NOT_FOUND("The provided organization ID was not found."),
    USER_NOT_FOUND("User not found with given id: %s"),
    ORGANIZATION_ALREADY_EXISTS("Organization name already exists."),
    TASK_NOT_FOUND("Task is empty, not found");
    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }
    public String format (Object...args){
        return  String.format(this.message,args);
    }


}