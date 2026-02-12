package com.team3.capstone.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team3.capstone.backend.entity.Ticket;
import com.team3.capstone.backend.entity.User;
import com.team3.capstone.backend.repository.TicketRepository;
import com.team3.capstone.backend.repository.UserRepository;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Get All Tickets
    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // 🔹 Assign Ticket to Agent
    @PutMapping("/tickets/{ticketId}/assign/{agentId}")
    public ResponseEntity<String> assignTicket(
            @PathVariable Long ticketId,
            @PathVariable Long agentId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User agent = userRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        if (!agent.getRole().equals("AGENT")) {
            return ResponseEntity.badRequest()
                    .body("User is not an AGENT");
        }

        ticket.setAssignedTo(agent);
        ticketRepository.save(ticket);

        return ResponseEntity.ok("Ticket assigned successfully");
    }
}
