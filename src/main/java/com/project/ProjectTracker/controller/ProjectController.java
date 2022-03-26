package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.entity.Project;
import com.project.ProjectTracker.entity.Task;
import com.project.ProjectTracker.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value="/getprojects/search/{project}")
    public List<Project> getProject(@PathVariable("project") String projectName)
    {
        return projectService.getProjectSearch(projectName);
    }

    @GetMapping(value="/getprojects/page/{page}")
    public List<Project> getProject(@PathVariable("page") int page)
    {
        return projectService.getProject(page);
    }

    @GetMapping(value="/getprojects")
    public List<Project> getProjects()
    {
        return projectService.getProjects();
    }

    @GetMapping(value="/getproject/{id}")
    public ResponseEntity<?> getProjects(@PathVariable("id") long pId)
    {
        Task task=projectService.getProjects(pId);
        if(task!=null)
            return ResponseEntity.ok(task);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = {"/count/{title}", "/count"})
    public long getCount(@PathVariable(name="title", required = false ) String title)
    {
        System.out.println("title = " + title);
        if(title!=null && !title.equals(""))
            return projectService.getCountByTitle(title);
        return projectService.getCount();
    }

}
