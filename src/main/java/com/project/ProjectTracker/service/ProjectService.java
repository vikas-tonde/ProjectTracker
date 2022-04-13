package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.ProjectRepository;
import com.project.ProjectTracker.Dao.TaskRepository;
import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.entity.Project;
import com.project.ProjectTracker.entity.Task;
import com.project.ProjectTracker.entity.User;
import com.project.ProjectTracker.models.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public long getCount() {
        return projectRepository.count();
    }

    public long getCountByTitle(String title) {
        return projectRepository.countByTitleStartingWith(title);
    }

    public List<ProjectDto> getProjectSearch(String title) {
        Optional<List<Project>> projects = projectRepository.findByTitleStartingWith(title);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return projects.map(projectList -> projectList.stream()
                .map(project -> modelMapper.map(project, ProjectDto.class))
                .collect(Collectors.toList())).orElse(null);
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public ProjectResponse getProjects(long id) {
        ProjectResponse projectResponse = new ProjectResponse();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<Task> tasks = taskRepository.findAllByProject_pId(id).orElse(null);
        if (tasks != null && (!tasks.isEmpty())) {
            projectResponse.setProject(modelMapper.map(tasks.get(0).getProject(),ProjectDto.class));
            List<TaskInfo> taskInfoList = tasks.stream()
                    .map(task -> modelMapper.map(task, TaskInfo.class))
                    .collect(Collectors.toList());
            projectResponse.setTaskInfoList(taskInfoList);
        } else {
            Optional<Project> project = projectRepository.findById(id);
            project.ifPresent(value -> projectResponse.setProject(modelMapper.map(value, ProjectDto.class)));
        }
        List<Intern> interns = userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, Intern.class))
                .collect(Collectors.toList());
        projectResponse.setInterns(interns);
        return projectResponse;
    }

    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public List<ProjectDto> getProject(int page) {
        Pageable pageable = PageRequest.of((page - 1), 4);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        return projectRepository.findAll(pageable).stream()
                .map(project -> modelMapper.map(project,ProjectDto.class))
                .collect(Collectors.toList());
    }

    //Update project

    @Transactional
    public boolean updateProjectTask(ProjectUpdateRequest projectUpdateRequest) {
        List<TaskInfo> taskInfoList = projectUpdateRequest.getTaskInfoList();
        Optional<Project> project = projectRepository.findById(projectUpdateRequest.getPId());
        List<Task> tasks = taskInfoList.stream()
                .map(taskInfo -> {
                            Task task = new Task();
                            Optional<User> user = userRepository.findByUsername(taskInfo.getUsername());
                            user.ifPresent(task::setUser);
                            project.ifPresent(task::setProject);
                            modelMapper.map(taskInfo, task);
                            return task;
                        }
                )
                .collect(Collectors.toList());

        List<Task> tasksList = taskRepository.saveAll(tasks);
        return tasksList != null && !tasksList.isEmpty();
    }

}
