package com.xu.springsecurityoauth.http;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.http
 * springSecurityJwt
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuthTest {

    @Value("${com.xu.myOAuth2Properties.MYOAuth2ClientProperties[1].clientId}")
    private String CLIENT_ID;

    @Value("${com.xu.myOAuth2Properties.MYOAuth2ClientProperties[1].clientSecret}")
    private String CLIENT_SECRET;

    @Test
    public void test1() {
        System.out.println(CLIENT_ID);
        System.out.println(getBasicAuthHeader());
    }

    /**
     * 构建header
     *
     * @return
     */
    private String getBasicAuthHeader() {
        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        byte[] encodedAuth;
        try {
            encodedAuth = Base64.encodeBase64(auth.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var5) {
            throw new RuntimeException(var5);
        }
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
