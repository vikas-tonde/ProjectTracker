package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.entity.Task;
import com.project.ProjectTracker.models.UserInfoResponse;
import com.project.ProjectTracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/users/page/{page}")
    public List<UserInfoResponse> getAllUsersPage(@PathVariable("page") int page)
    {
        return userService.getAllUsersPage(page);
    }

    @GetMapping("/users/tasks")
    public List<Task> getAllTasks(){
        return userService.getTasks();
    }
}
