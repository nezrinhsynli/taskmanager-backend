package com.example.taskmanager_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequest {

    @Size(min = 3, message = "Organization name must be at least 3 characters long")
    private String organizationName;

    @Pattern(regexp = "^\\+?\\d{7,15}$", message = "Phone number format is invalid")
    private String phoneNumber;

    private String address;

    @Pattern(regexp = "^[A-Za-zƏəÖöÜüĞğÇçŞşİı]+$", message = "Only letters can be used!")
    @Size(max = 20, message = "Name cannot be more than 20 characters!")
    private String adminName;

    @Pattern(regexp = "^[A-Za-zƏəÖöÜüĞğÇçŞşİı]+$", message = "Only letters can be used!")
    @Size(max = 30, message = "Surname cannot be more than 30 characters!")
    private String adminSurname;

    @Email(message = "Email must be in a valid format")
    private String adminEmail;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

}
