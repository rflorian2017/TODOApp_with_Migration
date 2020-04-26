package com.mac.model;

import javax.persistence.*;

@Entity
@Table(name = "subtask")
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtask_id")
    private int id;

    private String description;

    @ManyToOne
    private Task task;
}
