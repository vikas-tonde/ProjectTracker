package com.project.ProjectTracker.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pId;
    private String title;
    private Date dateAdded;
    private Date deadline;
    private long cost;
    private String priority;  //according to deadline(moderate, low, high)
    private String description;
    private String progress;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "project_tag",
            joinColumns = { @JoinColumn(name = "p_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private List<Tag> tags = new ArrayList<Tag>();

    @OneToMany(targetEntity = Task.class, mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;

}
