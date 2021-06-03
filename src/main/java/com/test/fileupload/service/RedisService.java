package com.test.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public void setOnlyStrEx(String key, String value, int seconds) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, seconds);
    }

    public String getOnlyString(String key) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
}
