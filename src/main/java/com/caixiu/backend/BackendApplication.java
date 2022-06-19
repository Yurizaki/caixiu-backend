package com.caixiu.backend;

import com.holmes.aws.vocabulary.AwsDynamoDbVocabulary;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@SpringBootApplication
@Configuration
public class BackendApplication implements CommandLineRunner {

	String host = "mongodb://localhost:27017";
	String database = "CaixiuDB";
	private DynamoDbClient dynamoDbClient;

	@Bean
	public AwsDynamoDbVocabulary awsDynamoDbVocabulary() {
		dynamoDbClient = DynamoDbClient.builder()
				.region(Region.EU_WEST_2)
				.build();

		return new AwsDynamoDbVocabulary(dynamoDbClient);
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) { }
}
