package com.xu.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xuhongda on 2018/10/23
 * com.xu.web.controller
 * springSecurityJwt
 */
@RestController
public class AboutToken {

    @GetMapping("about")
    public Object xx(Authentication authentication) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> detail = (Map<String, Object>) details.getDecodedDetails();
        Object girl = detail.get("girl");
        String s = mapper.writeValueAsString(authentication);
        return girl;
    }
}
