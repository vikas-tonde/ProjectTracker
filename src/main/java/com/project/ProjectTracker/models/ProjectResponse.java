package com.project.ProjectTracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private ProjectDto project;
    private List<TaskInfo> taskInfoList;
    private List<Intern> interns;
}
