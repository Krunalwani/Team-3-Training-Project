package com.team3.capstone.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.capstone.backend.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Tickets assigned to an agent (CORRECT)
    List<Ticket> findByAssignedTo_UserId(Long agentId);
    
 // Tickets created by user
    List<Ticket> findByCreatedBy_UserId(Long userId);
    
    
}
