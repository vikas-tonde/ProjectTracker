package com.project.ProjectTracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInfo {
    private long taskId;
    private String username;
    private String task;
    private Date assigned_on;
    private Date completed_on;
    private boolean completed;
}
