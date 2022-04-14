package com.project.ProjectTracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client")
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

}
