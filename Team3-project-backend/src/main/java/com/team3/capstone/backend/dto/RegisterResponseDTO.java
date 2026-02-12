package com.team3.capstone.backend.dto;

public class RegisterResponseDTO {
	private Long userId;
	private String message;
	private boolean success;
	
	//default constructor
	public void RegisterResponse() {
	}
	
	//parameterized constructor
	public void RegisterResponse(Long userId,String message,boolean success) {
		this.userId = userId;
		this.message = message;
		this.success = success;
	}
	
	//getter and setter method
	public Long getUserId() {
		return userId;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
