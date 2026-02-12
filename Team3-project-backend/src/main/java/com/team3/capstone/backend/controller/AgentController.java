package com.team3.capstone.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.team3.capstone.backend.dto.AgentTicketResponseDTO;
import com.team3.capstone.backend.service.TicketService;

import com.team3.capstone.backend.dto.AgentUpadateStatusRequestDTO;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private TicketService ticketService;

    //View assigned tickets
    @GetMapping("/ticket/{agentId}")
    public List<AgentTicketResponseDTO> getAgentTickets(@PathVariable Long agentId) {
        return ticketService.getTicketsByAgent(agentId);
    }

    //Update ticket status
    @PutMapping("/update-status/{ticketId}")
    public AgentTicketResponseDTO updateStatus(
            @PathVariable Long ticketId,
            @RequestBody AgentUpadateStatusRequestDTO requestDTO) {

        return ticketService.updateTicketStatus(ticketId, requestDTO.getStatus());
    }
}
