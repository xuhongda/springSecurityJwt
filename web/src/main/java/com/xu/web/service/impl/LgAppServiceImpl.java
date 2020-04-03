package com.xu.web.service.impl;

import com.xu.web.pojo.LgResponse;
import com.xu.web.service.LgAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @author xuhongda on 2018/9/11
 * com.xu.web.service.impl
 * springSecurityJwt
 */
@Slf4j
@Service
public class LgAppServiceImpl implements LgAppService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LgResponse appScanner(String lgToken) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Boolean aBoolean = valueOperations.getOperations().hasKey(lgToken);
        LgResponse lgResponse = new LgResponse();
        Assert.isTrue(aBoolean != null,"aBoolean 不为空");
        if (aBoolean) {
            lgResponse.setCode("1");
            lgResponse.setABoolean(true);
        } else {
            lgResponse.setABoolean(false);
        }

        Long expire = valueOperations.getOperations().getExpire(lgToken);
        Assert.isTrue(expire != null,"expire 不为空");
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

        //解析jwt ;颁发一个新的jwt令牌...从 authentication 中获取用户名，密码 发送一个获取新 token 的请求。

        String newJwt = "this is .....a new jwt !!! ";
        log.info("principal = {}",authentication.getPrincipal());
        Assert.isTrue(aBoolean != null,"aBoolean 不为空");
        if (aBoolean) {
            lgResponse.setABoolean(aBoolean);
            lgResponse.setCode("2");
            lgResponse.setJwt(newJwt);
            Long expire = valueOperations.getOperations().getExpire(lgtoken);
            Assert.isTrue(expire != null,"expire 不为空");
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
