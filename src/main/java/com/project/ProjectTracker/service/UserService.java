package com.project.ProjectTracker.service;


import com.project.ProjectTracker.Dao.TaskRepository;
import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.entity.Task;
import com.project.ProjectTracker.entity.User;
import com.project.ProjectTracker.models.UserInfoResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        Pageable pageable = PageRequest.of((page - 1), 4);
        Page<User> users = userRepository.findAll(pageable);
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
        return users.stream()
                .map(user -> modelMapper.map(user, UserInfoResponse.class))
                .collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public User addUser(User user) {
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        return userRepository.save(user);
    }
}
