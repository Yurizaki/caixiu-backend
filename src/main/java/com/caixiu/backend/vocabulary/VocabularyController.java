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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.caixiu.backend.BackendApplication.API_VERSION_1_0;

@RestController
public class VocabularyController {

    public static final String ENDPOINT_NAME_1_0 = "/vocabularies";
    public static final String BATCH_ENDPOINT_NAME_1_0 = "/batchVocabularies";

    public static final String GET_ALL_PATH_1_0 = API_VERSION_1_0 + ENDPOINT_NAME_1_0;
    public static final String GET_ITEM_PATH_1_0 = API_VERSION_1_0 + ENDPOINT_NAME_1_0 + "/{key}";
    public static final String POST_ITEM_PATH_1_0 = API_VERSION_1_0 + ENDPOINT_NAME_1_0;
    public static final String DELETE_ITEM_PATH_1_0 = API_VERSION_1_0 + ENDPOINT_NAME_1_0 + "/{key}";
    public static final String POST_BATCH_ITEM_PATH_1_0 = API_VERSION_1_0 + BATCH_ENDPOINT_NAME_1_0;

    private static final Logger LOGGER = LoggerFactory.getLogger(VocabularyController.class);

    @Autowired
    AwsDynamoDbVocabulary awsDynamoDbVocabulary;

    @GetMapping(path = GET_ALL_PATH_1_0)
    public List<Vocabulary> getAllVocabulary() {
        LOGGER.info("Get All Vocabulary");

        return awsDynamoDbVocabulary.getAllItems();
    }

    @GetMapping(path = GET_ITEM_PATH_1_0)
    public Vocabulary getVocabulary(@PathVariable String key) {
        LOGGER.info("Getting Vocabulary for: " + key);

        return awsDynamoDbVocabulary.getVocabulary(key);
    }

    @RequestMapping(path = POST_ITEM_PATH_1_0, method = {RequestMethod.POST, RequestMethod.PUT},
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vocabulary putVocabulary(@RequestBody Vocabulary vocabulary) {
        LOGGER.info("Putting new Vocabulary: " + vocabulary);

        awsDynamoDbVocabulary.putVocabulary(vocabulary);
        return awsDynamoDbVocabulary.getVocabulary(vocabulary.getK_vocab_id());
    }

    @RequestMapping(path = POST_BATCH_ITEM_PATH_1_0, method = {RequestMethod.POST, RequestMethod.PUT},
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void putBatchVocabulary(@RequestBody List<Vocabulary> vocabularyList) {
        LOGGER.info("Putting batch of vocabulary: " + vocabularyList.size());

        awsDynamoDbVocabulary.putBatchItem(vocabularyList);
    }

    @DeleteMapping(path = DELETE_ITEM_PATH_1_0, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vocabulary deleteVocabulary(@PathVariable String key) {
        LOGGER.info("Deleting Vocabulary with key: " + key);

        return awsDynamoDbVocabulary.deleteVocabulary(key);
    }
}