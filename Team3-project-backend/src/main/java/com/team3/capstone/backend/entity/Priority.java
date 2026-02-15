package com.team3.capstone.backend.entity;

import java.util.Set;

import jakarta.persistence.*;
@Entity
@Table(name = "priorities") // 🔥 EXACT DB TABLE NAME
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_id")
    private Long priorityId;

    @Column(name = "priority_name")
    private String priorityName;

    // getters & setters
}
