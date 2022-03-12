package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.Dao.ProjectRepository;
import com.project.ProjectTracker.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController
{
    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping(value = "/addproject")
    public boolean addProject(@RequestBody Project project)
    {
       projectRepository.save(project);
       return true;
    }

    @GetMapping(value="/getproject/{id}")
    public Project getProject(@PathVariable("id") long id)
    {
        return null;
    }

    @GetMapping(value="/getprojects")
    public List<Project> getProjects()
    {
        return null;
    }


}
