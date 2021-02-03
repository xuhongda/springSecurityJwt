package com.xu.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xuhongda on 2019/5/17
 * com.xu.springsecurityoauth.controller
 * springSecurityJwt
 */
@Slf4j
@RestController
public class ControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("hello")
    public String login(){
        return "success !";
    }

    @PostMapping("pp")
    public String pp(){
        return "pp !";
    }

    @GetMapping("about")
    public Object about(Authentication authentication) throws JsonProcessingException {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> detail = (Map<String, Object>) details.getDecodedDetails();
        log.info("authentication = {}", mapper.writeValueAsString(authentication));
        log.info("details = {}", details);
        return detail;
    }
}
