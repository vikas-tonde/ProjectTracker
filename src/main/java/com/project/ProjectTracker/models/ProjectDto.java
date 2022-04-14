package com.project.ProjectTracker.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDto {
    private long pId;
    private String title;
    private Date dateAdded;
    private Date deadline;
    private long cost;
    //    private String priority;  //according to deadline(moderate, low, high)
    private String description;
    private String progress;
    private String technologies;
    private String clientName;
}
