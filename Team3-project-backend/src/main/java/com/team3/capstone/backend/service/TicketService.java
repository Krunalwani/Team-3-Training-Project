package com.team3.capstone.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team3.capstone.backend.dto.AgentTicketResponseDTO;
import com.team3.capstone.backend.entity.enums.TicketStatus;

@Service
public interface TicketService {

	List<AgentTicketResponseDTO> getTicketsByAgent(Long agentId);

	

	AgentTicketResponseDTO updateTicketStatus(Long ticketId, String string);



	AgentTicketResponseDTO updateTicketStatus(Long ticketId, TicketStatus status);

}
