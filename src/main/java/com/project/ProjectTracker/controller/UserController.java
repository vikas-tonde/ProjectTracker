package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.entity.User;
import com.project.ProjectTracker.models.UserInfoResponse;
import com.project.ProjectTracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/users/page/{page}")
    public List<UserInfoResponse> getAllUsersPage(@PathVariable("page") int page) {
        return userService.getAllUsersPage(page);
    }

    @GetMapping("/user/tasks/{username}")
    public ResponseEntity<?> getAllTasks(@PathVariable String username) {
        if (!username.equals("") && username != null)
            return ResponseEntity.ok(userService.getTasks(username));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Add user

    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/user/info/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        UserInfoResponse userInfoResponse = userService.getUser(username);
        if (userInfoResponse!=null)
            return ResponseEntity.ok(userInfoResponse);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Edit User

    @PostMapping("/user/update")
    public boolean updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }

}
