package com.example.taskmanager_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "organization")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "admin_name", nullable = false)
    private String adminName;

    @Column(name = "admin_surname", nullable = false)
    private String adminSurname;

    @Column(name = "admin_email", nullable = false)
    private String adminEmail;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy="organization")
    private List<User> users;

    @OneToMany(mappedBy="organization")
    private List<Task> tasks;

}