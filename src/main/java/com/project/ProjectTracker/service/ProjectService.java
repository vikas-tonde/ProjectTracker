package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.ProjectRepository;
import com.project.ProjectTracker.entity.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public List<Project> getProject(String title)
    {
            Optional<List<Project>> project = projectRepository.findByTitleStartingWith(title);
            return project.orElse(null);
    }

    public List<Project> getProjects()
    {
        return projectRepository.findAll();
    }

    @Transactional
    public Project save(Project project) {
       return projectRepository.save(project);
    }
}
