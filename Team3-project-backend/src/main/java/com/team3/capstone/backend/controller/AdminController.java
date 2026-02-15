package com.team3.capstone.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.team3.capstone.backend.entity.Ticket;
import com.team3.capstone.backend.entity.User;
import com.team3.capstone.backend.entity.enums.Role;
import com.team3.capstone.backend.repository.TicketRepository;
import com.team3.capstone.backend.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all tickets
    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Get all agents
    @GetMapping("/agents")
    public List<User> getAllAgents() {
        return userRepository.findByRole(Role.AGENT);
    }

    // Assign ticket
    @PutMapping("/tickets/{ticketId}/assign/{agentId}")
    public ResponseEntity<String> assignTicket(
            @PathVariable Long ticketId,
            @PathVariable Long agentId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        if (!agent.getRole().equals(Role.AGENT)) {
            return ResponseEntity.badRequest().body("User is not an agent");
        }

        ticket.setAssignedTo(agent);
        ticketRepository.save(ticket);

        return ResponseEntity.ok("Ticket Assigned Successfully");
    }
}
