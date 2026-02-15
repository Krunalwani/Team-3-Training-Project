package com.team3.capstone.backend.entity;

import java.time.LocalDateTime;
import java.util.Set;

import com.team3.capstone.backend.entity.enums.TicketStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TicketCategory category;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    public Long getTicketId() {
		return ticketId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public TicketCategory getCategory() {
		return category;
	}

	public Priority getPriority() {
		return priority;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public Set<TicketComment> getComments() {
		return comments;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(TicketCategory category) {
		this.category = category;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setComments(Set<TicketComment> comments) {
		this.comments = comments;
	}

	@ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private Set<TicketComment> comments;

//	public Long getId() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public Long getId() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public Long getAgentId() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Long getId() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
