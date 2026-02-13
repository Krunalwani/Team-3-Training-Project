package com.team3.capstone.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.team3.capstone.backend.service.TicketService;
import com.team3.capstone.backend.serviceImp.TicketServiceImp;

@SpringBootApplication
public class Team3ProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team3ProjectBackendApplication.class, args);
	}
	
}
