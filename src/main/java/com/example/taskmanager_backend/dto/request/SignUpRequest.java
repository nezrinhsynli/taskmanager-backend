package com.example.taskmanager_backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Only letters and numbers can be used!")
    @Size(max = 20, message = "Name cannot be more than 20 characters!")
    private String name;

    @NotNull(message = "Email cannot be empty!")
    @Email(message = "Enter a valid email address!")
    private String email;

    @NotNull(message = "Password cannot be empty!")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters!")
    private String password;

}
