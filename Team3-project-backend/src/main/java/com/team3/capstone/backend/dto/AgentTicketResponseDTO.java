package com.team3.capstone.backend.dto;

public class AgentTicketResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private Long userId;
    private Long agentId;

    // Empty Constructor (Required)
    public AgentTicketResponseDTO() {
    }

    // Parameterized Constructor
    public AgentTicketResponseDTO(Long id, String title, String description,
                                  String status, Long userId, Long agentId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.agentId = agentId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
