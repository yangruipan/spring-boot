package com.buy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * redisService
 * @author yrp
 */
@Repository
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void addStr(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void add(String key, String value, Long time) {
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    public String get(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(value)) {
            return value;
        }
        return null;
    }

    public void delete(String key) {
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }
}
