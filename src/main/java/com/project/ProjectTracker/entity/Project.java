package com.project.ProjectTracker.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "project", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pId;
    private String title;
    private Date dateAdded;
    private Date deadline;
    private long cost;
    //    private String priority;  //according to deadline(moderate, low, high)
    private String description;
    private String progress;

    //    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "project_technology",
//            joinColumns = {@JoinColumn(name = "p_id")},
//            inverseJoinColumns = {@JoinColumn(name = "t_id")})
//    private List<Technology> technologies = new ArrayList<>();

    private String technologies;

    //    @OneToMany(targetEntity = Task.class, mappedBy = "project", fetch = FetchType.EAGER)
//    private List<Task> tasks;

}
