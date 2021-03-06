package com.project.ProjectTracker.service;


import com.project.ProjectTracker.Dao.TaskRepository;
import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.entity.Project;
import com.project.ProjectTracker.entity.Task;
import com.project.ProjectTracker.entity.User;
import com.project.ProjectTracker.models.CountResponse;
import com.project.ProjectTracker.models.TaskDto;
import com.project.ProjectTracker.models.UserInfoResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private TaskRepository taskRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserInfoResponse> getAllUsersPage(int page) {

        //TODO: do custom mapping of user and task

        Pageable pageable = PageRequest.of((page - 1), 6);
        Page<User> users = userRepository.findAll(pageable);
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
        return users.stream()
                .map(user -> modelMapper.map(user, UserInfoResponse.class))
                .collect(Collectors.toList());
    }

    public long getCountByUsername(String username) {
        return userRepository.countByUsernameStartingWith(username);
    }

    public List<UserInfoResponse> getUserSearch(String username) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Optional<List<User>> users = userRepository.findByUsernameStartsWith(username);
        return users.map(users1 -> users1.stream()
                .map(user -> modelMapper.map(user, UserInfoResponse.class))
                .collect(Collectors.toList())
        ).orElse(null);
    }

    public List<TaskDto> getTasks(String username) {
        List<Task> tasks = taskRepository.findAllByUser_UsernameAndCompletedIsFalse(username);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<TaskDto> taskDtos = tasks.stream().map(
                        task ->
                                modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());

        return taskDtos;
    }

    public UserInfoResponse getUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            UserInfoResponse userInfoResponse = modelMapper.map(optionalUser.get(), UserInfoResponse.class);
            List<Task> tasks = taskRepository.findAllByUser_Username(userInfoResponse.getUsername());
            Set<Project> projects = tasks.stream().map(Task::getProject)
                    .collect(Collectors.toSet());
            userInfoResponse.setProjects(projects);
            return userInfoResponse;
        }
        return null;
    }

    @Transactional
    public User addUser(User user) {
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        return userRepository.save(user);
    }

    public boolean updateUser(User user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            User userToSave = optionalUser.get();
            BeanUtils.copyProperties(user, userToSave, getNullPropertyNames(user));

            User user1 = userRepository.save(userToSave);
            return user1.getUId() != 0;
        }
        return false;
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        HashSet emptyNames = new HashSet();
        for (java.beans.PropertyDescriptor pd : pds) {
            //check if value of this property is null then add it to the collection
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return (String[]) emptyNames.toArray(result);
    }

    public long getCount() {
        return userRepository.count();
    }

    public CountResponse getCounts(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        int completedProjects = taskRepository.countCompletedProjects(user.get().getUId());
        int workingProjects = taskRepository.countWorkingProjects(user.get().getUId());
        CountResponse countResponse = new CountResponse(completedProjects, workingProjects);
        return countResponse;
    }
}
