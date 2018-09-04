package com.xu.security.springsecuritydemo.myTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author xuhongda on 2018/9/4
 * com.xu.security.springsecuritydemo.myTest
 * springSecurityJwt
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest1 {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * <p>
     *     jsonPath表达式: $ 表示根
     * </p>
     * @throws Exception
     */
    @Test
    public void contextLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user").param("name","xuhongda").param("age","18").contentType(MediaType.APPLICATION_JSON_UTF8)).
                andExpect(MockMvcResultMatchers.status().is(200)).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }


}
