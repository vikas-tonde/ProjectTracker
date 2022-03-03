package com.project.ProjectTracker.service;


import com.project.ProjectTracker.helper.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgetPasswordService {
    @Autowired
    EmailSender emailSender;

    public ForgetPasswordService() {

    }

    public ForgetPasswordService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public boolean sendMail(String email)
    {
        emailSender.sendMail(email);
        return true;
    }

}
