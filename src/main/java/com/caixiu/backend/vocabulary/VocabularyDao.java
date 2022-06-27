package com.caixiu.backend.vocabulary;

import com.caixiu.backend.CacheNames;
import com.caixiu.backend.dao.ItemDao;
import com.holmes.aws.vocabulary.AwsDynamoDbVocabulary;
import com.holmes.aws.vocabulary.Vocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

public class VocabularyDao implements ItemDao<Vocabulary> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VocabularyDao.class);

    private HashMap<CacheNames, List<Vocabulary>> vocabularyCache = new HashMap<CacheNames, List<Vocabulary>>();

    @Autowired
    AwsDynamoDbVocabulary awsDynamoDbVocabulary;

    public List<Vocabulary> getAll() {
        LOGGER.info("Loading from Database");
        return awsDynamoDbVocabulary.getAllItems();
    }

    public List<Vocabulary> getAllFromCache() {
        LOGGER.info("Loading from Cache");

        return this.vocabularyCache.get(CacheNames.VOCABULARY_CACHE);
    }

    public boolean hasCache() {
        return !vocabularyCache.get(CacheNames.VOCABULARY_CACHE).isEmpty();
    }

    public void loadCache() {
        this.vocabularyCache.put(CacheNames.VOCABULARY_CACHE, getAll());
    }

    public boolean reloadCache() {
        LOGGER.info("Reloading data into Cache");

        this.vocabularyCache.remove(CacheNames.VOCABULARY_CACHE);
        this.vocabularyCache.put(CacheNames.VOCABULARY_CACHE, getAll());

        return hasCache();
    }
}
