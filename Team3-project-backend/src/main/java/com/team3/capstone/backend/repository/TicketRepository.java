package com.team3.capstone.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.capstone.backend.entity.Ticket;
import com.team3.capstone.backend.entity.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Tickets created by a user
    List<Ticket> findByCreatedBy(User user);

    // Tickets assigned to an agent
    List<Ticket> findByAssignedTo(User user);

	List<Ticket> findByAgentId(Long agentId);
}
