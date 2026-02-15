package com.team3.capstone.backend.dto;

import com.team3.capstone.backend.entity.Priority;
import com.team3.capstone.backend.entity.enums.TicketStatus;

public class AgentTicketResponseDTO {

    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private Long userId;
    private Long agentId;
	private Priority priority;
	private String createdByUsername;
	private String assignedToUsername;

    // Empty Constructor
    public AgentTicketResponseDTO() {
    }

    // Parameterized Constructor
    public AgentTicketResponseDTO(Long id, String title, String description,
            TicketStatus status, Priority priority,
            String createdByUsername, String assignedToUsername) {
    			this.id = id;
    			this.title = title;
			this.description = description;
			this.status = status;
			this.priority = priority;
			this.createdByUsername = createdByUsername;
			this.assignedToUsername = assignedToUsername;
}


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

  

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }


}
