package com.team3.capstone.backend.service;

import java.util.List;

import com.team3.capstone.backend.dto.AgentTicketResponseDTO;
import com.team3.capstone.backend.entity.enums.TicketStatus;

public interface TicketService {

    List<AgentTicketResponseDTO> getTicketsByAgent(Long agentId);

    AgentTicketResponseDTO updateTicketStatus(Long ticketId, TicketStatus status);
    
    
}
