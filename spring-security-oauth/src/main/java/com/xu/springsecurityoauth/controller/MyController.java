package com.xu.springsecurityoauth.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author xuhongda on 2018/9/9
 * com.xu.springsecurityoauth.controller
 * springSecurityJwt
 */
@RestController
public class MyController {

    @GetMapping("me")
    public Object me(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
        String authorization = request.getHeader("Authorization");
        String bearer = StringUtils.substringAfter(authorization, "bearer");
        //验签默认并不是使用utf-8
        Claims xuhongda;
        if (bearer != null) {
            xuhongda = Jwts.parser().setSigningKey("xuhongda".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(bearer).getBody();
            Object girl = xuhongda.get("girl");
            System.out.println(girl);
        }
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getPrincipal());

        System.out.println(authentication);

        return authentication;
    }

    @GetMapping("test")
    public Object test() {
        return "hello !!!";
    }
}
