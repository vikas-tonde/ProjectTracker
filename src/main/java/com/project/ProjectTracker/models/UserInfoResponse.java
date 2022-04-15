package com.project.ProjectTracker.models;

import com.project.ProjectTracker.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {

    private long uId;
    private String firstName;
    private String lastName;
    private String username;
    private String address;
    private String phoneNo;
    private Date dob;
    private String email;
    private String qualification;

    private Set<Project> projects;
}
