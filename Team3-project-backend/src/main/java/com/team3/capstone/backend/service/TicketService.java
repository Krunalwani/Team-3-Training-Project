package com.team3.capstone.backend.service;

import java.util.List;

import com.team3.capstone.backend.dto.AgentTicketResponseDTO;

public interface TicketService {

	List<AgentTicketResponseDTO> getTicketsByAgent(Long agentId);

	AgentTicketResponseDTO updateTicketStatus(Long ticketId, String status);

}
