package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
