package com.project.ProjectTracker.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phase", uniqueConstraints = {@UniqueConstraint(columnNames = {"phaseName"})})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Phase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phaseId;
    private String phaseName;
    private int percentage;
}
