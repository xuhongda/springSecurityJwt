package com.xu.springsecurityoauth.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.http
 * springSecurityJwt
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuthTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Value("${com.xu.myOauth2Properties.myOauth2ClientProperties[0].clientId}")
    private String CLIENT_ID;

    @Value("${com.xu.myOauth2Properties.myOauth2ClientProperties[0].clientSecret}")
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

    @Test
    public void requestTokenWhenUsingPasswordGrantTypeThenOk()
            throws Exception {

        this.mockMvc.perform(post("/oauth/token")
                .param("grant_type", "password")
                .param("username", "subject")
                .param("password", "password")
                .header("Authorization", getBasicAuthHeader()))
                .andExpect(status().isOk());
    }

    @Test
    public void requestJwkSetWhenUsingDefaultsThenOk()
            throws Exception {

        this.mockMvc.perform(get("/.well-known/jwks.json"))
                .andExpect(status().isOk());
    }
}
