package com.project.ProjectTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;
/*
This class is the entity class of User which is used
to map the object with user table by spring data JPA
 */

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String address;
    private String phoneNo;
    private Date dob;
    private String email;
    private String roles;
    private String verificationCode;
    private String qualification;
    private boolean otpVerified;

    @OneToMany(targetEntity = Task.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Task> tasks;


    public User(User user) {
        this.uId = user.getUId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.address = user.getAddress();
        this.phoneNo = user.getPhoneNo();
        this.dob = user.getDob();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.verificationCode = user.getVerificationCode();
        this.otpVerified= user.isOtpVerified();
        this.firstName= user.getFirstName();
        this.lastName= user.getLastName();
        this.qualification= user.getQualification();

    }

}
