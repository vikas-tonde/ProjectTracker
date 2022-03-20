package com.project.ProjectTracker.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tId;
    private String technologyName;
}
