package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
