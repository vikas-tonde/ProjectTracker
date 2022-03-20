package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.entity.Project;
import com.project.ProjectTracker.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins="*")
public class ProjectController
{
    private ProjectService projectService;

    @PostMapping(value = "/addproject")
    public Project addProject(@RequestBody Project project)
    {
       return projectService.save(project);
    }

    @GetMapping(value="/getprojects/{project}")
    public List<Project> getProject(@PathVariable("project") String projectName)
    {
        return projectService.getProject(projectName);
    }

    @GetMapping(value="/getprojects")
    public List<Project> getProjects()
    {
        return projectService.getProjects();
    }


}
