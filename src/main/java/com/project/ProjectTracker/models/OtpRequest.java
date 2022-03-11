package com.project.ProjectTracker.models;

public class OtpRequest {
    private String username;
    private String otp;

    public OtpRequest() {
    }

    public OtpRequest(String username, String otp) {
        this.username = username;
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
