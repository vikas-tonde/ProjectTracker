package com.project.ProjectTracker.models;

import com.project.ProjectTracker.entity.Project;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Set<Project> projects= new HashSet<>();
    private List<TaskInfo> taskInfoList;

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects.addAll(projects);
    }

    public List<TaskInfo> getTaskInfoList() {
        return taskInfoList;
    }

    public void setTaskInfoList(List<TaskInfo> taskInfoList) {
        this.taskInfoList = taskInfoList;
    }
}
