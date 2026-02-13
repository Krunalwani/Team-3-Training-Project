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

	public Long getUserId() {
		return userId;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Set<Ticket> getCreatedTickets() {
		return createdTickets;
	}

	public Set<Ticket> getAssignedTickets() {
		return assignedTickets;
	}

	public Set<TicketComment> getComments() {
		return comments;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setCreatedTickets(Set<Ticket> createdTickets) {
		this.createdTickets = createdTickets;
	}

	public void setAssignedTickets(Set<Ticket> assignedTickets) {
		this.assignedTickets = assignedTickets;
	}

	public void setComments(Set<TicketComment> comments) {
		this.comments = comments;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
