package com.team3.capstone.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.capstone.backend.entity.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    // Find priority by level (LOW, MEDIUM, HIGH)
    Optional<Priority> findByPriorityLevel(String priorityLevel);

    // Check if priority already exists
    boolean existsByPriorityLevel(String priorityLevel);
}
