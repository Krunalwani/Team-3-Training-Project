package com.team3.capstone.backend.entity;

import java.time.LocalDateTime;
import java.util.Set;

import com.team3.capstone.backend.entity.enums.AccountStatus;
import com.team3.capstone.backend.entity.enums.Role;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "createdBy")
    private Set<Ticket> createdTickets;

    @OneToMany(mappedBy = "assignedTo")
    private Set<Ticket> assignedTickets;

    @OneToMany(mappedBy = "user")
    private Set<TicketComment> comments;
}
