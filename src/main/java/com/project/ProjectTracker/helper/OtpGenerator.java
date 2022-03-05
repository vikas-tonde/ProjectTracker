package com.project.ProjectTracker.helper;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OtpGenerator {
    private StringBuilder otp;

    public String sendOtp()  {
        SecureRandom secureRandom = new SecureRandom();
        otp = new StringBuilder();
//        secureRandom=SecureRandom.getInstance(secureRandom.getAlgorithm());
        for(int i=0;i<6;i++)
        {
            otp.append(secureRandom.nextInt(0,9));
        }
        return otp.toString();
    }
}
