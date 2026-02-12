package com.team3.capstone.backend.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminControllerUsingDTO {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Get All Tickets
    @GetMapping("/tickets")
    public List<TicketResponseDTO> getAllTickets() {

        return ticketRepository.findAll()
                .stream()
                .map(ticket -> new TicketResponseDTO(
                        ticket.getId(),
                        ticket.getTitle(),
                        ticket.getDescription(),
                        ticket.getStatus(),
                        ticket.getPriority(),
                        ticket.getCreatedBy().getUsername(),
                        ticket.getAssignedTo() != null ?
                                ticket.getAssignedTo().getUsername() : null
                ))
                .toList();
    }

    // 🔹 Assign Ticket to Agent
    @PutMapping("/tickets/{ticketId}/assign/{agentId}")
    public AssignTicketResponseDTO assignTicket(
            @PathVariable Long ticketId,
            @PathVariable Long agentId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        if (!agent.getRole().equals("AGENT")) {
            throw new RuntimeException("User is not an AGENT");
        }

        ticket.setAssignedTo(agent);
        ticketRepository.save(ticket);

        return new AssignTicketResponseDTO(
                "Ticket assigned successfully",
                ticket.getId(),
                agent.getUsername()
        );
    }
}