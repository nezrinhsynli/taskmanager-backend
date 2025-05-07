package com.example.taskmanager_backend.repository;

import com.example.taskmanager_backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String name);
    boolean existsByEmail(String email);

}
