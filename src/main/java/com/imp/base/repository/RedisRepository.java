package com.imp.base.repository;

import java.util.Map;

public interface RedisRepository {

    public Map<Object, Object> getAllObjects();

    public void addObject(String key, Object object);

    public Object getObjectByKey(final String id);

    public void deleteByKey(String key);

    public void deleteAllKeyValues();


}
