package com.team3.capstone.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.capstone.backend.entity.User;
import com.team3.capstone.backend.entity.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

	List<User> findByRole(Role role);
}
