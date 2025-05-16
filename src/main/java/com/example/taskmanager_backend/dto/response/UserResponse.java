package com.example.taskmanager_backend.dto.response;

import com.example.taskmanager_backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;

}
