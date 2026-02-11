package com.team3.capstone.backend.entity;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "priorities")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priorityId;

    @Column(nullable = false, unique = true)
    private String priorityLevel; // LOW, MEDIUM, HIGH

    @OneToMany(mappedBy = "priority")
    private Set<Ticket> tickets;
}
