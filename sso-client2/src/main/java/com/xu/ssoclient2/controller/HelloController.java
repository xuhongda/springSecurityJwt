package com.xu.ssoclient2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhongda on 2018/9/10
 * com.xu.ssoclient2.controller
 * springSecurityJwt
 */
@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello() {
        return "hello client ";
    }
}
