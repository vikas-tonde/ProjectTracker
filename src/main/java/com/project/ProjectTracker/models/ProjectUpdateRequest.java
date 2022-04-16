package com.project.ProjectTracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProjectUpdateRequest {
    private long pId;
    private Date deadline;
    private String description;
    private String phaseName;
    private List<TaskInfo> taskInfoList;
}
