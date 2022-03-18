package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
