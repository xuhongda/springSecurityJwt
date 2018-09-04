package com.xu.security.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuhongda on 2018/9/4
 * com.xu.security.springsecuritydemo.controller
 * springSecurityJwt
 */
@Controller
public class HelloController {
    @ResponseBody
    @GetMapping("/")
    public String index(){
        return "hello!";
    }

    @GetMapping("hello")
    public String hello(){
        return "hello.html";
    }
}
