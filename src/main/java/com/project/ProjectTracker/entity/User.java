package com.project.ProjectTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
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

//    @OneToMany(targetEntity = Task.class, mappedBy = "user", fetch = FetchType.EAGER)
//    private List<Task> tasks;


}
