package com.caixiu.backend.vocabulary;

import com.caixiu.backend.user.model.User;
import com.caixiu.backend.user.repository.UserRepository;
//import com.holmes.crud.read.DataReader;
//import com.holmes.vocabulary.Vocabulary;
import com.holmes.aws.vocabulary.AwsDynamoDbVocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;

@RestController
public class VocabularyController {

    @Autowired
    AwsDynamoDbVocabulary awsDynamoDbVocabulary;


    @RequestMapping(value = "/vocabularies")
    public String getUser() {


        awsDynamoDbVocabulary.getAllItems();

        return "dataReader.findAll()";
    }
}