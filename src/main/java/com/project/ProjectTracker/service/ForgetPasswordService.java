package com.project.ProjectTracker.service;


import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.entity.User;
import com.project.ProjectTracker.helper.EmailSender;
import com.project.ProjectTracker.helper.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgetPasswordService {
    @Autowired
    private EmailSender emailSender;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpGenerator otpGenerator;


    public ForgetPasswordService() {

    }

    public ForgetPasswordService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public boolean sendMail(String username)
    {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isPresent())
        {
            String otp = otpGenerator.sendOtp();
            User u = user.get();
            u.setVerificationCode(otp);
            userRepository.save(u);
            emailSender.sendMail(user.get().getEmail(),user.get().getUsername(), otp);
            return true;
        }
        else {
            return false;

        }
    }

    public boolean verifyOtp(String otp, String username)
    {
        Optional <User> user= userRepository.findByUsernameAndVerificationCode(username, otp);
        if(user.isPresent()) {
            User u = user.get();
            u.setOtpVerified(true);
            u.setVerificationCode(null);
            userRepository.save(u);
            return true;
        }
        else
            return false;
    }

    public boolean resetPassword(String username, String password)
    {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent())
        {
            User u = user.get();
            if(u.isOtpVerified())
            {
                String encode = bCryptPasswordEncoder.encode(password);
                u.setPassword(encode);
                u.setOtpVerified(false);
                userRepository.save(u);
                return true;
            }
        }
        return false;
    }

}
