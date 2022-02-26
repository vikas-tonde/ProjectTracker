package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.entity.Role;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @PostMapping({"/createNewRole"})
    public Role createNewRole(Role role){

        return role;
    }
}
