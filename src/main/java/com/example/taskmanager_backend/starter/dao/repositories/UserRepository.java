package com.example.taskmanager_backend.starter.dao.repositories;

import com.example.taskmanager_backend.starter.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}
