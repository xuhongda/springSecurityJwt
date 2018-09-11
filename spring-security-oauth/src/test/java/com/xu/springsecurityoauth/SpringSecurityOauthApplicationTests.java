package com.xu.springsecurityoauth;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;


public class SpringSecurityOauthApplicationTests {

    @Test
    public void contextLoads() {

        System.out.println(getBasicAuthHeader("xuhongda", "7777_a"));
    }


    /**
     * 构建header
     *
     * @return
     */
    private String getBasicAuthHeader(String id, String secret) {
        String auth = id + ":" + secret;
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
