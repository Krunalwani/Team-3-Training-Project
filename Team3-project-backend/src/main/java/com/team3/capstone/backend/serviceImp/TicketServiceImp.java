package com.team3.capstone.backend.serviceImp;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.capstone.backend.dto.AgentTicketResponseDTO;
import com.team3.capstone.backend.entity.Ticket;
import com.team3.capstone.backend.entity.enums.TicketStatus;
import com.team3.capstone.backend.repository.TicketRepository;
import com.team3.capstone.backend.service.TicketService;

@Service
public class TicketServiceImp implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    //Get all tickets assigned to a specific agent
    @Override
    public List<AgentTicketResponseDTO> getTicketsByAgent(Long agentId) {

        List<Ticket> tickets = ticketRepository.findByAgentId(agentId);

        return tickets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //Update ticket status
    @Override
    public AgentTicketResponseDTO updateTicketStatus(Long ticketId, TicketStatus status) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));

        ticket.setStatus(status);

        Ticket updatedTicket = ticketRepository.save(ticket);

        return convertToDTO(updatedTicket);
    }

    //Convert Entity to DTO
    private AgentTicketResponseDTO convertToDTO(Ticket ticket) {

        AgentTicketResponseDTO dto = new AgentTicketResponseDTO();

        dto.setId(ticket.getTicketId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());
        dto.setAgentId(ticket.getAgentId());

        return dto;
    }

	@Override
	public AgentTicketResponseDTO updateTicketStatus(Long ticketId, String string) {
		// TODO Auto-generated method stub
		return null;
	}
}

