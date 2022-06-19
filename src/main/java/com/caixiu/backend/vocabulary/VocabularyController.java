package com.caixiu.backend.vocabulary;

import com.holmes.aws.vocabulary.AwsDynamoDbVocabulary;
import com.holmes.aws.vocabulary.Vocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VocabularyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VocabularyController.class);

    @Autowired
    AwsDynamoDbVocabulary awsDynamoDbVocabulary;

    @GetMapping(path = "/vocabularies")
    public List<Vocabulary> getAllVocabulary() {
        LOGGER.info("Get All Vocabulary");
        return awsDynamoDbVocabulary.getAllItems();
    }

    @GetMapping(path = "/vocabularies/{key}")
    public Vocabulary getVocabulary(@PathVariable String key) {
        LOGGER.info("Getting Vocabulary for: " + key);
        return awsDynamoDbVocabulary.getVocabulary(key);
    }

    @RequestMapping(path = "/vocabularies", method = {RequestMethod.POST, RequestMethod.PUT},
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vocabulary putVocabulary(@RequestBody Vocabulary vocabulary) {
        LOGGER.info("Putting new Vocabulary: " + vocabulary);
        awsDynamoDbVocabulary.putVocabulary(vocabulary);
        return awsDynamoDbVocabulary.getVocabulary(vocabulary.getK_vocab_id());
    }

    @RequestMapping(path = "/batchVocabularies", method = {RequestMethod.POST, RequestMethod.PUT},
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void putBatchVocabulary(@RequestBody List<Vocabulary> vocabularyList) {
        LOGGER.info("Putting batch of vocabulary: " + vocabularyList.size());
        awsDynamoDbVocabulary.putBatchItem(vocabularyList);
    }

    @DeleteMapping(path = "/vocabularies/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vocabulary deleteVocabulary(@PathVariable String key) {
        LOGGER.info("Deleting Vocabulary with key: " + key);
        return awsDynamoDbVocabulary.deleteVocabulary(key);
    }
}