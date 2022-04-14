package com.project.ProjectTracker.models;


import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDto {
    private long pId;
    private String title;
    private Date dateAdded;
    private Date deadline;
    private long cost;
    //    private String priority;  //according to deadline(moderate, low, high)
    private String description;
    private String progress;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private String technologies;

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }
}
