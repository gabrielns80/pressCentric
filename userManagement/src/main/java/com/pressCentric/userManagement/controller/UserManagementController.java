package com.pressCentric.userManagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pressCentric.userManagement.UserRepository;
import com.pressCentric.userManagement.entity.User;

@RestController
@RequestMapping("/rest")
public class UserManagementController {

	@Autowired
	private UserRepository userRepository;


	@GetMapping("/users")
	public Response200 getAllUsers() {
		List<User> data = userRepository.findAll();
		
		Response200 response = new Response200("200", "List of all Users", data);
		return response;
	}


	@GetMapping("/users/{id}")
	public Response200 getUserById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		List<User> data = new ArrayList<User>();
		data.add(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Id #"  + id + " not found: ")));
		
		Response200 response = new Response200("200", "Requested User's details", data);
		return response;
	}


	@PostMapping("/users")
	public Response204 createUser(@Valid @RequestBody String user) {
		Gson gson = new Gson();
		User convertedUser = gson.fromJson(user, User.class);
		userRepository.save(convertedUser);
		
		Response204 response = new Response204("204", "User created succefully");
		return response;
	}


	@PutMapping("/users/{id}")
	public Response204 updateUser( @PathVariable(value = "id") Integer id, @Valid @RequestBody String userInfo) throws ResourceNotFoundException {
		Gson gson = new Gson();
		User convertedUserInfo = gson.fromJson(userInfo, User.class);
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Id #"  + id + " not found: "));
		user.setName(convertedUserInfo.getName());
		user.setEmail(convertedUserInfo.getEmail());
		userRepository.save(user);
		
		Response204 response = new Response204("204", "User modified succefully");
		return response;
	}


	@DeleteMapping("/users/{id}")
	public Response204 deleteUser(@PathVariable(value = "id") Integer id) throws Exception {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Id #"  + id + " not found: "));
		userRepository.delete(user);
		
		Response204 response = new Response204("204", "User deleted succefully");
		return response;
	}
}