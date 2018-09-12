package com.xu.springsecurityoauth.http;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author xuhongda on 2018/9/12
 * com.xu.springsecurityoauth.http
 * springSecurityJwt
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScannerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testQuery() throws Exception {
        while (true) {
            ResultActions perform = mockMvc.perform(MockMvcRequestBuilders
                    .get("/qrcodelogin")
                    .header("Authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsiYWxsIiwicmVhZCIsIndyaXRlIl0sImV4cCI6MTUzNjc1ODMwMSwiZ2lybCI6InlhbiIsImF1dGhvcml0aWVzIjpbImFkbWluIl0sImp0aSI6IjYzMjBlOTk3LThjZjgtNDVhNi1hNTc3LTM2ZDY1N2M1Y2I1NyIsImNsaWVudF9pZCI6Inh1aG9uZ2RhIiwiaG9tZSI6ImppYW5neGkifQ.O9jlUVKgb_O2lpFmJ0n72xcwawg9NZuu4DV4DG1HLlk")
                    .param("lgtoken", "123456")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isOk())
                    .andDo(print());
            System.out.println(perform.andReturn().getResponse().getContentType());
            Thread.sleep(100);
        }


    }
}
