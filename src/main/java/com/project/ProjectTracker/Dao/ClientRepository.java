package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.Client;
import com.project.ProjectTracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByProjects(Project projects);

    Client findByClientName(String clientName);


    Optional<List<Client>> findAllByClientNameStartingWith(String clientName);

    long countByClientNameStartingWith(String clientName);

}
