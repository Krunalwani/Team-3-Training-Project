package com.team3.capstone.backend.entity;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_categories")
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, unique = true)
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Ticket> tickets;
}
