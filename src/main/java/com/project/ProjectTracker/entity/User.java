package com.project.ProjectTracker.entity;

import jakarta.persistence.*;

import java.sql.Date;
/*
This class is the entity class of User which is used
to map the object with user table by spring data JPA
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uId;
    private String username;
    private String password;
    private String address;
    private String phoneNo;
    private Date dob;
    private String email;
    private String roles;
    private String verificationCode;
    private boolean otpVerified;

    public User(int uId,
                String username,
                String password,
                String address,
                String phoneNo,
                Date dob,
                String email,
                String roles,
                String verificationCode, boolean otpVerified) {
        this.uId = uId;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.email = email;
        this.roles = roles;
        this.verificationCode = verificationCode;
        this.otpVerified= otpVerified;
    }

    public User(String username,
                String password,
                String address,
                String phoneNo,
                Date dob,
                String email,
                String roles,
                String verificationCode, boolean otpVerified) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.email = email;
        this.roles = roles;
        this.verificationCode = verificationCode;
        this.otpVerified= otpVerified;
    }

    public User() {

    }

    public User(User user) {
        this.uId = user.getuId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.address = user.getAddress();
        this.phoneNo = user.getPhoneNo();
        this.dob = user.getDob();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.verificationCode = user.getVerificationCode();
        this.otpVerified= user.isOtpVerified();
    }


    public String getAddress() {
        return address;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    User(String username){
        this.username=username;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOtpVerified() {
        return otpVerified;
    }

    public void setOtpVerified(boolean otpVerified) {
        this.otpVerified = otpVerified;
    }
}
