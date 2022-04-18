package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.*;
import com.project.ProjectTracker.entity.*;
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
    private ClientRepository clientRepository;
    private PhaseRepository phaseRepository;
    private PhaseService phaseService;

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


    public ProjectResponse getProjects(long id) {
        ProjectResponse projectResponse = new ProjectResponse();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<Task> tasks = taskRepository.findAllByProject_pId(id).orElse(null);
        Project project;
        if (tasks != null && (!tasks.isEmpty())) {
            projectResponse.setProject(modelMapper.map(tasks.get(0).getProject(), ProjectDto.class));
            projectResponse.setPhaseName(tasks.get(0).getProject().getPhase().getPhaseName());
            project = tasks.get(0).getProject();
            List<TaskInfo> taskInfoList = tasks.stream()
                    .map(task -> modelMapper.map(task, TaskInfo.class))
                    .collect(Collectors.toList());
            projectResponse.setTaskInfoList(taskInfoList);
        } else {
            Optional<Project> project1 = projectRepository.findById(id);
            project1.ifPresent(value -> {
                        projectResponse.setProject(modelMapper.map(value, ProjectDto.class));
                        projectResponse.setPhaseName(value.getPhase().getPhaseName());
                    }
            );
            project = project1.get();
        }
        projectResponse.setExpectedPhaseCompletionDate(phaseService.getExpectedCompletionDate(project));
        List<Intern> interns = userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, Intern.class))
                .collect(Collectors.toList());
        projectResponse.setInterns(interns);
        return projectResponse;
    }

    @Transactional
    public Project save(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        Client client = clientRepository.findByClientName(projectDto.getClientName());
        Optional<Phase> phase = phaseRepository.findById(1L);
        phase.ifPresent(project::setPhase);
        project.setProgress("0");
        Project savedProject = projectRepository.save(project);
        List<Project> projects = client.getProjects();
        projects.add(savedProject);
        client.setProjects(projects);
        clientRepository.save(client);
        return savedProject;
    }

    public List<ProjectDto> getProject(int page) {
        Pageable pageable = PageRequest.of((page - 1), 4);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        return projectRepository.findAll(pageable).stream()
                .map(project -> modelMapper.map(project, ProjectDto.class))
                .collect(Collectors.toList());
    }

    //Update project

    @Transactional
    public boolean updateProjectTask(ProjectUpdateRequest projectUpdateRequest) {
        System.out.println("projectUpdateRequest = " + projectUpdateRequest);
        List<TaskInfo> taskInfoList = projectUpdateRequest.getTaskInfoList();
        Optional<Project> project = projectRepository.findById(projectUpdateRequest.getPId());
        String phaseName = projectUpdateRequest.getPhaseName();

        if (project.isPresent()) {
            if (projectUpdateRequest.getDeadline() != null) {
                project.get().setDeadline(projectUpdateRequest.getDeadline());
            }
            if (projectUpdateRequest.getDescription() != null) {
                project.get().setDescription(projectUpdateRequest.getDescription());
            }
            if (phaseName != null) {
                Phase phase = phaseRepository.findByPhaseName(phaseName);
                project.get().setPhase(phase);
            }
            project.get().setProgress(phaseService.getProgress(project.get().getPhase()));
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
        return false;
    }

    public List<String> getHighPriorityProjects()
    {
        List<Project> projects = projectRepository.findHighPriorityProjects();
        return projects.stream()
                .map(Project::getTitle)
                .collect(Collectors.toList());
    }

    public List<ProjectProgress> getAllProjectProgress()
    {
        List<Project> projects = projectRepository.findAll();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return projects.stream()
                .map(project -> modelMapper.map(project, ProjectProgress.class))
                .collect(Collectors.toList());
    }

    public CountResponse getCountCompleted()
    {
        int completed = projectRepository.countCompleted();
        int notCompleted = projectRepository.countNotCompleted();
        return new CountResponse(completed,notCompleted);

    }

    public List[] getAllProjectCost()
    {
        List<Project> projects = projectRepository.findAll();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<String> titles = projects.stream()
                .map(Project::getTitle)
                .collect(Collectors.toList());

        List<Long> costs = projects.stream()
                .map(Project::getCost)
                .collect(Collectors.toList());
        return new List[]{titles,costs};
    }
}
