package com.pressCentric.userManagement.controller;

import java.util.List;

import com.pressCentric.userManagement.entity.User;

public class Response200 {
	
	private String code;
	private String message;
	private List<User> user;
	

	public Response200(String code, String message, List<User> user) {
		this.code = code;
		this.message = message;
		this.user = user;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}

}
