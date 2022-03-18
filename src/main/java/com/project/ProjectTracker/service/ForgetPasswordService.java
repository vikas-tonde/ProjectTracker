package com.project.ProjectTracker.service;


import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.entity.User;
import com.project.ProjectTracker.helper.EmailSender;
import com.project.ProjectTracker.helper.OtpGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ForgetPasswordService {

    private EmailSender emailSender;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository userRepository;

    private OtpGenerator otpGenerator;

    @Transactional
    public boolean sendMail(String username)
    {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isPresent())
        {
            String otp = otpGenerator.sendOtp();
            User u = user.get();
            u.setVerificationCode(otp);
            userRepository.save(u);
            emailSender.sendMail(u.getEmail(),u.getUsername(), otp);
            return true;
        }
        return false;
    }

    @Transactional
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
        return false;
    }

    @Transactional
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
