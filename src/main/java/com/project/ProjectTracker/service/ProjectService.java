package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.ProjectRepository;
import com.project.ProjectTracker.Dao.TaskRepository;
import com.project.ProjectTracker.entity.Project;
import com.project.ProjectTracker.entity.Task;
import com.project.ProjectTracker.models.ProjectResponse;
import com.project.ProjectTracker.models.TaskInfo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;

    public long getCount()
    {
        return projectRepository.count();
    }

    public long getCountByTitle(String title)
    {
        return projectRepository.countByTitleStartingWith(title);
    }

    public List<Project> getProjectSearch(String title)
    {
            Optional<List<Project>> project = projectRepository.findByTitleStartingWith(title);
            return project.orElse(null);
    }

    public List<Project> getProjects()
    {
        return projectRepository.findAll();
    }

    public ProjectResponse getProjects(long id)
    {
        ProjectResponse projectResponse= new ProjectResponse();

        List<Task> tasks=taskRepository.findAllByProject_pId(id).orElse(null);
        Set<Project> projects = tasks.stream().map(task -> task.getProject()).collect(Collectors.toSet());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        projectResponse.setProjects(projects);
        List<TaskInfo> taskInfoList = tasks.stream()
                .map(task -> modelMapper.map(task, TaskInfo.class))
                .collect(Collectors.toList());
        projectResponse.setTaskInfoList(taskInfoList);
        return projectResponse;
    }

    @Transactional
    public Project save(Project project) {
       return projectRepository.save(project);
    }

    public List<Project> getProject(int page) {
        Pageable pageable= PageRequest.of((page-1),4);

        return projectRepository.findAll(pageable).stream().toList();
    }
}
