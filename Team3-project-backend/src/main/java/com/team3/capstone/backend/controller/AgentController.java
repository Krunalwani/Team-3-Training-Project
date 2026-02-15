package com.team3.capstone.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.team3.capstone.backend.dto.AgentTicketResponseDTO;
import com.team3.capstone.backend.dto.AgentUpadateStatusRequestDTO;
import com.team3.capstone.backend.service.TicketService;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "*")
public class AgentController {

    @Autowired
    private TicketService ticketService;

    // 1️⃣ View tickets assigned to agent
    @GetMapping("/tickets/{agentId}")
    public List<AgentTicketResponseDTO> getAgentTickets(@PathVariable Long agentId) {
        return ticketService.getTicketsByAgent(agentId);
    }

    // 2️⃣ Update ticket status
    @PutMapping("/tickets/{ticketId}/status")
    public AgentTicketResponseDTO updateStatus(
            @PathVariable Long ticketId,
            @RequestBody AgentUpadateStatusRequestDTO requestDTO) {

        return ticketService.updateTicketStatus(ticketId, requestDTO.getStatus());
    }
}
