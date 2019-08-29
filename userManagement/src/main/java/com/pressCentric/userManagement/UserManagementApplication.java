package com.pressCentric.userManagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pressCentric.userManagement.entity.User;

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			repository.save(new User("Isaac", "jack@presscentric.com"));
			repository.save(new User("Michelle", "michelle@presscentric.com"));
			repository.save(new User("Oscar", "oscar@presscentric.com"));
			repository.save(new User("David", "david@presscentric.com"));
			repository.save(new User("Ryan", "ryan@presscentric.com"));
		};
	}

}
