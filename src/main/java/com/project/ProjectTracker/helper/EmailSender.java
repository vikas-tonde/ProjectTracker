package com.project.ProjectTracker.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String toMail)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toMail);
        message.setSubject("Password Reset");
        message.setText("This is your code buddy");
        javaMailSender.send(message);
    }
}
