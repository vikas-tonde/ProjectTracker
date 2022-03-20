package com.project.ProjectTracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_id")
    private Project project;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "u_id")
    private User user;

    private String task;
    private Date assigned_on;
    private Date completed_on;
}
