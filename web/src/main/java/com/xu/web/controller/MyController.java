package com.xu.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author xuhongda on 2018/9/9
 * com.xu.web.controller
 * springSecurityJwt
 */
@Slf4j
@RestController
public class MyController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("me")
    public Object me(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
        String authorization = request.getHeader("Authorization");
        String bearer = StringUtils.substringAfter(authorization, "Bearer");
        //验签默认并不是使用utf-8
        Claims xuhongda;
        if (bearer != null) {
            xuhongda = Jwts.parser().setSigningKey("xuhongda&yan".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(bearer).getBody();
            Object girl = xuhongda.get("girl");
            log.info("my girl = {}",girl);
        }
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getCredentials());

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();

        log.info("authentication = {}",authentication);
        log.info("details = {}",details);

        return authentication;
    }

    @GetMapping("test")
    public Object test() {
        return "hello !!!";
    }

    @GetMapping("jwt")
    public Object jwt() {
        return null;

    }
}
