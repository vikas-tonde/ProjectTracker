package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long>
{
    Optional<List<Project>> findByTitleStartingWith(String title);

    long countByTitleStartingWith(String title);

    @Query(value ="select * from project where progress<>'100' order by deadline asc limit 5" ,nativeQuery = true)
    List<Project> findHighPriorityProjects();

    @Query(value = "select count(p_id) from project where progress='100'",nativeQuery = true)
    int countCompleted();

    @Query(value = "select count(p_id) from project where progress<>'100'",nativeQuery = true)
    int countNotCompleted();
}
