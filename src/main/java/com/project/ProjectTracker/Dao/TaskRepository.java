package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{
    Optional<List<Task>> findAllByProject_pId(long id);
}