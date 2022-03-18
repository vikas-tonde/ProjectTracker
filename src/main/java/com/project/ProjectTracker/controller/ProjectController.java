package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.entity.Project;
import com.project.ProjectTracker.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProjectController
{
    private ProjectService projectService;


    @PostMapping(value = "/addproject")
    public Project addProject(@RequestBody Project project)
    {
       return projectService.save(project);
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
