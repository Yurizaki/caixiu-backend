package com.caixiu.backend;

import com.caixiu.backend.dao.ItemDao;
import com.caixiu.backend.vocabulary.VocabularyDao;
import com.holmes.aws.vocabulary.AwsDynamoDbVocabulary;
import com.holmes.aws.vocabulary.Vocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.Map;


@SpringBootApplication
@Configuration
public class BackendApplication implements CommandLineRunner{

    private static final Logger LOGGER = LoggerFactory.getLogger(BackendApplication.class);
    public static final String API_VERSION_1_0 = "/v1.0";

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.EU_WEST_2)
                .build();
    }

    @Bean
    public VocabularyDao vocabularyDao() {
        return new VocabularyDao();
    }

    @Bean
    public AwsDynamoDbVocabulary awsDynamoDbVocabulary(DynamoDbClient dynamoDbClient) {
        return new AwsDynamoDbVocabulary(dynamoDbClient);
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Starting up application");
        loadCaches();
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
                .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        map.forEach((key, value) -> LOGGER.info("{} {}", key, value));
    }

    private void loadCaches() {
        vocabularyDao().loadCache();
    }
}
