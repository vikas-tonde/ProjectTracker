package com.project.ProjectTracker.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tag", uniqueConstraints = {@UniqueConstraint(columnNames = {"tag_name"})})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    private String tagName;
}
