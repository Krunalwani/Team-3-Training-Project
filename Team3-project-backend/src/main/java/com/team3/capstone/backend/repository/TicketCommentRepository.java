package com.team3.capstone.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.capstone.backend.entity.Ticket;
import com.team3.capstone.backend.entity.TicketComment;

@Repository
public interface TicketCommentRepository extends JpaRepository<TicketComment, Long> {

    List<TicketComment> findByTicket(Ticket ticket);
}
