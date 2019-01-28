package com.xu.springsecurityoauth.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.http
 * springSecurityJwt
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuthTest {

    @Value("${com.xu.myOAuth2Properties.MyOauth2ClientProperties[1].clientId}")
    private String CLIENT_ID;

    @Value("${com.xu.myOAuth2Properties.MyOauth2ClientProperties[1].clientSecret}")
    private String CLIENT_SECRET;

    @Test
    public void test1() {
        System.out.println(CLIENT_ID);
        System.out.println(getBasicAuthHeader());
    }

    /**
     * 构建获取token header
     */
    private String getBasicAuthHeader() {
        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        log.info("authHeader = {}", authHeader);
        return authHeader;
    }
}
