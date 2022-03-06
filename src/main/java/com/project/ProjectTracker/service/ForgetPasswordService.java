package com.project.ProjectTracker.service;


import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.entity.User;
import com.project.ProjectTracker.helper.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgetPasswordService {
    @Autowired
    EmailSender emailSender;

    @Autowired
    UserRepository userRepository;


    public ForgetPasswordService() {

    }

    public ForgetPasswordService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public boolean sendMail(String username)
    {
        System.out.println(username);
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isPresent())
        {
            emailSender.sendMail(user.get().getEmail(),user.get().getUsername());
            return true;
        }
        else {
            return false;

        }
    }

}
