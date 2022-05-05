package com.imp.base.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

    Logger logger = LoggerFactory.getLogger(RedisRepositoryImpl.class);
    private static final String KEY = "Redis";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;
    
    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public Map<Object, Object> getAllObjects(){
        logger.debug("Getting all static data.");
        return hashOperations.entries(KEY);
    }

    public void addObject(String key, Object object) {
        logger.debug("Adding static data.");
        hashOperations.put(KEY, key, object);
    }

    public Object getObjectByKey(final String id) {
        logger.debug("Getting static data by key.");
        return hashOperations.get(KEY, id);
    }

    public void deleteByKey(final String id) {
        logger.debug("Deleting static data.");
        hashOperations.delete(KEY, id);
    }

    public void deleteAllKeyValues() {
        logger.debug("Deleting all key/values.");
        try {
            Set<String> keys = hashOperations.keys(KEY);
            logger.debug("Found this many matching keys - " + keys.size());

            for (String key : keys) {
                this.deleteByKey(key);
            }

        } catch (Exception e) {
            logger.debug("Exception deleting all keys.");
        }
    }

}
