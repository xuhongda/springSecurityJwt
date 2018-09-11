package com.xu.springsecurityoauth.service.impl;

import com.xu.springsecurityoauth.pojo.LgResponse;
import com.xu.springsecurityoauth.service.LgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author xuhongda on 2018/9/11
 * com.xu.springsecurityoauth.service.impl
 * springSecurityJwt
 */
@Service
public class LgServiceImpl implements LgService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LgResponse saveLgToken(String lgToken) {

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(lgToken, null, 45L, TimeUnit.SECONDS);

        LgResponse lgResponse = new LgResponse();

        lgResponse.setABoolean(true);

        lgResponse.setCode("0");

        return lgResponse;
    }

    @Override
    public LgResponse query(String lgtoken) {

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Boolean aBoolean = valueOperations.getOperations().hasKey(lgtoken);

        LgResponse response = (LgResponse) valueOperations.get(lgtoken);

        if (response != null) {
            if (response.getJwt() != null) {
                valueOperations.getOperations().delete(lgtoken);
            }
        } else {
            LgResponse lgResponse = new LgResponse();

            lgResponse.setABoolean(aBoolean);
            lgResponse.setCode("0");
            return lgResponse;
        }
        return response;
    }


}
