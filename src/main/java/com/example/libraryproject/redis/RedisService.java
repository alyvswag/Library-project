package com.example.libraryproject.redis;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisService {

    final RedissonClient redissonClient;

    public void set(String tokenName,String token,Long milliSeconds){
        RBucket<String> key = redissonClient.getBucket(tokenName);
        key.set(token,Duration.of(milliSeconds, ChronoUnit.MILLIS));
    }
    public boolean exists(String tokenName){
        RBucket<String> key = redissonClient.getBucket(tokenName);
        return key.isExists();
    }


}
