package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OtpVerificationService {
    @Autowired
    UserRepository userRepository;

    public boolean verify(String otp, String username){
        Optional <User> user= userRepository.findByUsernameAndVerificationCode(username, otp);
        if(user.isPresent())
            return true;
        else
            return false;
    }
}
