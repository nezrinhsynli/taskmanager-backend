package com.example.taskmanager_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.taskmanager_backend"})
@ComponentScan(basePackages = {"com.example.taskmanager_backend"})
@EnableJpaRepositories(basePackages = {"com.example.taskmanager_backend"})
public class TaskmanagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerBackendApplication.class, args);
	}

}
