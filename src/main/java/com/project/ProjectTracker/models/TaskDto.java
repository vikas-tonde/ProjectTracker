package com.project.ProjectTracker.models;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskDto {
    private long taskId;
    private String title;
    private String task;
    private Date assigned_on;
}
