package com.xu.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
public class AboutToken {
    /**
     * 自定义TokenConvert - token 转换器 获得信息
     *
     * @param authentication 认证对象
     */
    @GetMapping("about")
    public Object about(Authentication authentication) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String tokenValue = details.getTokenValue();
        log.info("tokenValue = {}", tokenValue);
        Map<String, Object> detail = (Map<String, Object>) details.getDecodedDetails();
        Object girl = detail.get("girl");
        log.info(mapper.writeValueAsString(authentication));
        log.info("girl = {}", girl);
        return detail;
    }
}
