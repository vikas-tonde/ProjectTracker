package com.project.ProjectTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "client", uniqueConstraints = {@UniqueConstraint(columnNames = {"clientName"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long cId;
    private String clientName;
    private String representative;
    private String phoneNo;
    private String email;
    private String address;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_id")
    private List<Project> projects;

}
