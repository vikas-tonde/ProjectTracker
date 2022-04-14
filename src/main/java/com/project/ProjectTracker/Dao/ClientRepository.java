package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.Client;
import com.project.ProjectTracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByProjects(Project projects);

    public Client findByClientName(String clientName);

}
