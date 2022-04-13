package com.project.ProjectTracker.entity;


import lombok.*;

//@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Technology {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private long tId;
    private String technologyName;
}
