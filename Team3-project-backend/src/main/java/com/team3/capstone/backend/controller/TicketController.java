package com.team3.capstone.backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.team3.capstone.backend.dto.AgentTicketResponseDTO;
import com.team3.capstone.backend.dto.CreateTicketRequestDTO;
import com.team3.capstone.backend.dto.TicketResponseDTO;
import com.team3.capstone.backend.entity.Priority;
import com.team3.capstone.backend.entity.Ticket;
import com.team3.capstone.backend.entity.TicketCategory;
import com.team3.capstone.backend.entity.User;
import com.team3.capstone.backend.entity.enums.TicketStatus;
import com.team3.capstone.backend.repository.PriorityRepository;
import com.team3.capstone.backend.repository.TicketCategoryRepository;
import com.team3.capstone.backend.repository.TicketRepository;
import com.team3.capstone.backend.repository.UserRepository;
import com.team3.capstone.backend.service.TicketService;
@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketCategoryRepository categoryRepository;

    @Autowired
    private PriorityRepository priorityRepository;

    @Autowired
    private TicketService ticketService;

    // -----------------------------------------------------------
    // 1. CREATE TICKET (USER)
    // -----------------------------------------------------------
    @PostMapping("/create")
    public TicketResponseDTO createTicket(
            @RequestBody CreateTicketRequestDTO request
    ) {

        User createdBy = userRepository.findById(request.userId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

        TicketCategory category = categoryRepository.findById(request.categoryId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Category"));

        Priority priority = priorityRepository.findById(request.priorityId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Priority"));

        Ticket ticket = new Ticket();
        ticket.setTitle(request.title);
        ticket.setDescription(request.description);
        ticket.setCategory(category);
        ticket.setPriority(priority);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedBy(createdBy);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket saved = ticketRepository.save(ticket);

        return new TicketResponseDTO(
                "Ticket created successfully!",
                saved.getTicketId(),
                "Not Assigned Yet"
        );
    }

    // -----------------------------------------------------------
    // 2. GET ALL TICKETS (ADMIN)
    // -----------------------------------------------------------
    @GetMapping("/all")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // -----------------------------------------------------------
    // 3. GET TICKET BY ID
    // -----------------------------------------------------------
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));
    }

    // -----------------------------------------------------------
    // 4. ASSIGN TICKET TO AGENT (ADMIN)
    // -----------------------------------------------------------
    @PutMapping("/assign/{ticketId}/{agentId}")
    public TicketResponseDTO assignTicket(
            @PathVariable Long ticketId,
            @PathVariable Long agentId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));

        User agent = userRepository.findById(agentId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent not found"));

        if (!agent.getRole().name().equals("AGENT")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not an agent");
        }

        ticket.setAssignedTo(agent);
        ticket.setStatus(TicketStatus.IN_PROGRESS);
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket updated = ticketRepository.save(ticket);

        return new TicketResponseDTO(
                "Assigned successfully!",
                updated.getTicketId(),
                agent.getUsername()
        );
    }

    // -----------------------------------------------------------
    // 5. UPDATE TICKET STATUS (AGENT)
    // -----------------------------------------------------------
    @PutMapping("/status/{ticketId}")
    public AgentTicketResponseDTO updateStatus(
            @PathVariable Long ticketId,
            @RequestParam TicketStatus status
    ) {
        return ticketService.updateTicketStatus(ticketId, status);
    }

    // -----------------------------------------------------------
    // 6. DELETE TICKET
    // -----------------------------------------------------------
    @DeleteMapping("/{ticketId}")
    public String deleteTicket(@PathVariable Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));

        ticketRepository.delete(ticket);
        return "Ticket deleted successfully!";
    }

    // -----------------------------------------------------------
    // 7. GET ALL CATEGORIES (FRONTEND DROPDOWN)
    // -----------------------------------------------------------
    @GetMapping("/categories")
    public List<TicketCategory> getCategories() {
        return categoryRepository.findAll();
    }

    // -----------------------------------------------------------
    // 8. GET ALL PRIORITIES (FRONTEND DROPDOWN)
    // -----------------------------------------------------------
    @GetMapping("/priorities")
    public List<Priority> getPriorities() {
        return priorityRepository.findAll();
    }
    
 // -----------------------------------------------------------
 // 9. GET TICKETS BY USER (USER DASHBOARD) ✅ ADD THIS
 // -----------------------------------------------------------
 @GetMapping("/user/{userId}")
 public List<Ticket> getTicketsByUser(@PathVariable Long userId) {

     User user = userRepository.findById(userId)
             .orElseThrow(() ->
                     new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

     return user.getCreatedTickets()
             .stream()
             .toList();
 }
    
}
