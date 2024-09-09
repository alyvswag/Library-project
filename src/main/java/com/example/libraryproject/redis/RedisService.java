package com.example.libraryproject.redis;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisService {

    final RedissonClient redissonClient;

    public void set(String tokenName, String token, Long milliSeconds) {
        RBucket<String> key = redissonClient.getBucket(tokenName);
        key.set(token, Duration.of(milliSeconds, ChronoUnit.MILLIS));
    }

    public void addTokensToEmail(String email, String accessToken, String refreshToken) {
        RMap<String, String> tokenMap = redissonClient.getMap(email);
        tokenMap.put("accessToken", accessToken);
        tokenMap.put("refreshToken", refreshToken);
    }

    public String getRefreshTokenForEmail(String email) {
        RMap<String, String> tokenMap = redissonClient.getMap(email);
        return tokenMap.get("refreshToken");
    }

    public String getAccessTokenForEmail(String email) {
        RMap<String, String> tokenMap = redissonClient.getMap(email);
        return tokenMap.get("accessToken");
    }

    public Boolean exists(String tokenName) {
        RBucket<String> key = redissonClient.getBucket(tokenName);
        return key.isExists();
    }

    public void delete(String tokenName) {
        RBucket<String> key = redissonClient.getBucket(tokenName);
        key.delete();
    }
    public void deleteMap(String email) {
        RMap<String, String> tokenMap = redissonClient.getMap(email);
        tokenMap.delete();
    }

    public Boolean isTokenMine(String email,String refreshToken) {
        RMap<String, String> tokenMap = redissonClient.getMap(email);
        return refreshToken.equals(tokenMap.get("refreshToken"));
    }

}
