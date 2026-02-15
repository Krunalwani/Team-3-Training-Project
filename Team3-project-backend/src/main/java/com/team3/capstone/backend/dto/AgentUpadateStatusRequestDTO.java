package com.team3.capstone.backend.dto;

import com.team3.capstone.backend.entity.enums.TicketStatus;

public class AgentUpadateStatusRequestDTO {

    private TicketStatus status;

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
