package com.xu.springsecurityoauth.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * @author xuhongda on 2018/10/15
 * com.xu.springsecurityoauth.json
 * springSecurityJwt
 */
public class JsonTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test1() throws Exception {
        String json = "";
        String s = objectMapper.writeValueAsString(json);
        System.out.println(s);
    }

    @Test
    public void test2() {
        System.out.println("test");
    }
}
