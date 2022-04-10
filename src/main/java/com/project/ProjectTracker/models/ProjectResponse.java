package com.project.ProjectTracker.models;

import com.project.ProjectTracker.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Project project;
    private List<TaskInfo> taskInfoList;
    private List<Intern> interns;
}
