package com.project.ProjectTracker.helper;

import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    Configuration configuration;

    public void sendMail(String toMail,String username, String otp)
    {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message,true);


        helper.setTo(toMail);
        helper.setFrom("tondev98@gmail.com");
        helper.setSubject("Password Reset");
        helper.setFrom("tondev98@gmail.com");
        String content= getEmailContent(otp,username);
        helper.setText(content,true);
        javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    String getEmailContent(String otp, String username) {
        StringWriter stringWriter = new StringWriter();
        Map<String, String> model = new HashMap<>();
        model.put("otp", otp);
        model.put("username", username);
        try {
            configuration.getTemplate("index.ftlh").process(model, stringWriter);


        }  catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.getBuffer().toString();
    }
}
