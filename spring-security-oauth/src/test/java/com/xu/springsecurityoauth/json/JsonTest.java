package com.xu.springsecurityoauth.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author xuhongda on 2018/10/15
 * com.xu.springsecurityoauth.json
 * springSecurityJwt
 */
@Slf4j
public class JsonTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test1() throws Exception {
        String json = "";
        log.info(mapper.writeValueAsString(json));
    }

}
