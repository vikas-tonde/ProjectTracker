package com.project.ProjectTracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProjectUpdateRequest {
    private long pId;
    private String title;
    private Date dateAdded;
    private Date deadline;
    private long cost;

    private String description;
    private String progress;
}
