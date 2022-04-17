package com.project.ProjectTracker.Dao;

import com.project.ProjectTracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> findAllByProject_pId(long id);

    List<Task> findAllByUser_UsernameAndCompletedIsFalse(String username);

    List<Task> findAllByUser_Username(String username);

    @Query(value = "select count(distinct task.p_id) from task, project where project.progress<>'100' and task.u_id=?1 and task.p_id=project.p_id",
            nativeQuery = true)
    int countWorkingProjects(Long uId);

    @Query(value = "select count(distinct task.p_id) from task, project where project.progress='100' and task.u_id=?1 and task.p_id=project.p_id",
            nativeQuery = true)
    int countCompletedProjects(Long uId);
}