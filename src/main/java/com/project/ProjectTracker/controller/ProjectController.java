package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.entity.Project;
import com.project.ProjectTracker.models.ProjectDto;
import com.project.ProjectTracker.models.ProjectResponse;
import com.project.ProjectTracker.models.ProjectUpdateRequest;
import com.project.ProjectTracker.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProjectController {
    private ProjectService projectService;

    @PostMapping(value = "/project/add")
    public Project addProject(@RequestBody ProjectDto projectDto) {
        return projectService.save(projectDto);
    }

    @GetMapping(value = "/getprojects/search/{project}")
    public List<ProjectDto> getProject(@PathVariable("project") String projectName) {
        return projectService.getProjectSearch(projectName);
    }

    @GetMapping(value = "/getprojects/page/{page}")
    public List<ProjectDto> getProject(@PathVariable("page") int page) {
        return projectService.getProject(page);
    }

    @GetMapping(value = "/getprojects")
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping(value = "/getproject/{id}")
    public ResponseEntity<?> getProjects(@PathVariable("id") long pId) {
        ProjectResponse projectResponse = projectService.getProjects(pId);

        if (projectResponse != null)
            return ResponseEntity.ok(projectResponse);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = {"/count/{title}", "/count"})
    public long getCount(@PathVariable(name = "title", required = false) String title) {
        System.out.println("title = " + title);
        if (title != null && !title.equals(""))
            return projectService.getCountByTitle(title);
        return projectService.getCount();
    }

    //Edit existing project
    @PostMapping(value = "/project/update")
    public boolean updateProject(@RequestBody ProjectUpdateRequest projectUpdateRequest) {
        return projectService.updateProjectTask(projectUpdateRequest);

    }

}
