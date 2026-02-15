package com.team3.capstone.backend.controller;

import com.team3.capstone.backend.dto.AgentTicketResponseDTO;
import com.team3.capstone.backend.entity.Ticket;
import com.team3.capstone.backend.repository.TicketRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/admin/dto")
public class AdminControllerUsingDTO {

    private final TicketRepository ticketRepository;

    // Constructor Injection
    public AdminControllerUsingDTO(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // GET API to fetch all tickets
    @GetMapping("/tickets")
    public List<AgentTicketResponseDTO> getAllTickets() {

        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(ticket -> new AgentTicketResponseDTO(
                		ticket.getTicketId(),
                        ticket.getTitle(),
                        ticket.getDescription(),
                        ticket.getStatus(),
                        ticket.getPriority(),
                        ticket.getCreatedBy() != null 
                                ? ticket.getCreatedBy().getUsername() 
                                : null,
                        ticket.getAssignedTo() != null 
                                ? ticket.getAssignedTo().getUsername() 
                                : null
                ))
                .toList();
    }
}
