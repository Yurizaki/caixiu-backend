package com.caixiu.backend;

//import com.caixiu.backend.user.model.User;
//import com.holmes.CaixiuDbApplication;
//import com.holmes.crud.read.DataReader;
//import com.holmes.setup.CaixiuDbCollections;
//import com.holmes.setup.CaixiuMongoOperations;
//import com.holmes.vocabulary.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.caixiu.backend.user.repository.UserRepository;

import java.time.LocalDateTime;


@SpringBootApplication
@Configuration
public class BackendApplication implements CommandLineRunner {

//	@Autowired
//	UserRepository userRepository;

	String host = "mongodb://localhost:27017";
	String database = "CaixiuDB";
//	com.holmes.setup.CaixiuMongoOperations caixiuMongoOperations = new CaixiuMongoOperations(host, database);

//	@Bean
//	public DataReader<Vocabulary> dataReader() {
//		return new DataReader<>(
//				CaixiuDbCollections.VOCABULARY.getCollectionName(),
//				caixiuMongoOperations.getMongoOperations(),
//				Vocabulary.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


	@Override
	public void run(String... args) {
//		User user = userRepository.findUserByUsername("Bill");
//		if(user == null) {
//			userRepository.save(new User("2", "Bill", LocalDateTime.now()));
//			user = userRepository.findUserByUsername("Bill");
//		}
//
//		System.out.println("Item Name: " + user.getUsername());
	}
}
