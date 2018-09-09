package com.xu.springsecurityoauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhongda on 2018/9/9
 * com.xu.springsecurityoauth.controller
 * springSecurityJwt
 */
@RestController
public class MyController {

    @GetMapping("info")
    public Object info(Authentication authentication) {
        return authentication;
    }
}
