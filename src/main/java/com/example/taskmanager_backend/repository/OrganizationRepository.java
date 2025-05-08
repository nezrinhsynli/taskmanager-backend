package com.example.taskmanager_backend.repository;

import com.example.taskmanager_backend.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    boolean existsByAdminEmail(String email);
    boolean existsByOrganizationName(String email);

}
