package com.team3.capstone.backend.dto;

public class TicketResponseDTO {

    private String message;
    private Long ticketId;
    private String assignedAgent;

    public TicketResponseDTO(String message,
                                   Long ticketId,
                                   String assignedAgent) {
        this.message = message;
        this.ticketId = ticketId;
        this.assignedAgent = assignedAgent;
    }
            // getters
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getAssignedAgent() {
		return assignedAgent;
	}

	public void setAssignedAgent(String assignedAgent) {
		this.assignedAgent = assignedAgent;
	}

    
    
    
}