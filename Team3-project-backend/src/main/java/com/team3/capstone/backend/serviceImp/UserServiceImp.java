package com.team3.capstone.backend.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.team3.capstone.backend.entity.User;
import com.team3.capstone.backend.entity.enums.Role;
import com.team3.capstone.backend.repository.UserRepository;
import com.team3.capstone.backend.service.UserService;

public class UserServiceImp implements UserService{

private UserRepository userRepository;
	
	@Autowired
	private UserRepository userRepository1;

	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Long userId) {
		 return userRepository.findById(userId);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		 return Optional.empty();
	}

	@Override
	public List<User> getAllUsers() {
		 return userRepository.findAll();
	}

	@Override
	public void deleteUserById(Long userId) {
		 userRepository.deleteById(userId);
		
	}

	@Override
	public User updateUser(Long userId, User userDetails) {
		Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFullName(userDetails.getFullName());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setRole(userDetails.getRole());
            existingUser.setAccountStatus(userDetails.getAccountStatus());
            existingUser.setCreatedAt(userDetails.getCreatedAt());

            return userRepository.save(existingUser);
        }
        return null; // Or throw an exception if the user doesn't exist
    }
	
	@Override
	public List<User> getUsersByRole(Role role) {
		 return userRepository.findByRole(role);
	}

}
