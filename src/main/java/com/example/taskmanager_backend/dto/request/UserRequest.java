package com.example.taskmanager_backend.dto.request;

import com.example.taskmanager_backend.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @Pattern(regexp = "^[A-Za-zƏəÖöÜüĞğÇçŞşİı]+$", message = "Only letters can be used!")
    @Size(max = 20, message = "Name cannot be more than 20 characters!")
    private String name;

    @Pattern(regexp = "^[A-Za-zƏəÖöÜüĞğÇçŞşİı]+$", message = "Only letters can be used!")
    @Size(max = 30, message = "Surname cannot be more than 30 characters!")
    private String surname;

    @NotNull(message = "Email cannot be empty!")
    @Email(message = "Enter a valid email address!")
    private String email;

    @NotNull(message = "Password cannot be empty!")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters!")
    private String password;

    @NotNull(message = "The Role cannot be empty!")
    private Role role;

}
