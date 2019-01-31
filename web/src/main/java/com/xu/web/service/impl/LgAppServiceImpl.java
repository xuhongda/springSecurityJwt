package com.xu.web.service.impl;

import com.xu.web.pojo.LgResponse;
import com.xu.web.service.LgAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author xuhongda on 2018/9/11
 * com.xu.web.service.impl
 * springSecurityJwt
 */
@Service
public class LgAppServiceImpl implements LgAppService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LgResponse appScanner(String lgToken) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Boolean aBoolean = valueOperations.getOperations().hasKey(lgToken);
        LgResponse lgResponse = new LgResponse();
        if (aBoolean) {
            lgResponse.setCode("1");
            lgResponse.setABoolean(aBoolean);
        } else {
            lgResponse.setABoolean(false);
        }

        Long expire = valueOperations.getOperations().getExpire(lgToken);

        if (expire > 0) {
            //redis存储
            valueOperations.set(lgToken, lgResponse, expire, TimeUnit.SECONDS);
        }

        return lgResponse;
    }

    @Override
    public LgResponse appVerify(String lgtoken, Authentication authentication) {

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();

        Boolean aBoolean = valueOperations.getOperations().hasKey(lgtoken);


        LgResponse lgResponse = new LgResponse();

        //解析jwt ;颁发一个新的jwt令牌...

        String newJwt = "this is .....a new jwt !!! ";
        System.out.println(authentication.getPrincipal());

        if (aBoolean) {
            lgResponse.setABoolean(aBoolean);
            lgResponse.setCode("2");
            lgResponse.setJwt(newJwt);
            Long expire = valueOperations.getOperations().getExpire(lgtoken);
            if (expire > 0) {
                //redis存储
                valueOperations.set(lgtoken, lgResponse, expire, TimeUnit.SECONDS);
            }
        } else {
            lgResponse.setABoolean(false);

        }
        return lgResponse;
    }


    @Override
    public LgResponse appCancel(String lgtoken) {

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //redis删除
        valueOperations.getOperations().delete(lgtoken);

        LgResponse lgResponse = new LgResponse();
        lgResponse.setCode("3");
        lgResponse.setABoolean(false);
        return lgResponse;
    }
}
