package com.project.ProjectTracker.models;

import com.project.ProjectTracker.entity.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private long pId;
    private String title;
    private Date dateAdded;
    private Date deadline;
    private long cost;
    private String priority;
    private String description;
    private String progress;
    private List<Technology> technologies = new ArrayList<>();

    private long uId;
    private String username;
    private String qualification;

    private String task;
    private Date assigned_on;
    private Date completed_on;

}
