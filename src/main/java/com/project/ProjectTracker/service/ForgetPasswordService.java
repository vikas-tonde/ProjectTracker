package com.project.ProjectTracker.service;


import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.entity.User;
import com.project.ProjectTracker.helper.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user=userRepository.findByUsername(username).get();
        if(user!=null)
        {
            emailSender.sendMail(user.getEmail());
            return true;
        }
        else {
            return false;

        }
    }

}
