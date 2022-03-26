package com.caixiu.backend;

import com.caixiu.backend.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.caixiu.backend.user.repository.UserRepository;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableMongoRepositories
public class BackendApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) {
		User user = userRepository.findUserByUsername("alex");
		if(user == null) {
			userRepository.save(new User("1", "alex", LocalDateTime.now()));
		}

		System.out.println("Item Name: " + user.getUsername());
	}
}
