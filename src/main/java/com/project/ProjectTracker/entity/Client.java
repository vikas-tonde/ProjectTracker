package com.project.ProjectTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "client", uniqueConstraints = {@UniqueConstraint(columnNames = {"client_name"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cId;
    private String clientName;
    private String representative;
    private String phoneNo;
    private String email;
    private String address;

    @OneToMany
    @JoinColumn(name = "c_id")
    private List<Project> projects;

}
