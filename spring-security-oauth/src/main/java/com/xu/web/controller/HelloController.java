package com.xu.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhongda on 2019/6/5
 * com.xu.springsecurityoauth.controller
 * springSecurityJwt
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("h1")
    public String h1(){
        return "h1";
    }

    @GetMapping("h2")
    public String h2(){
        return "h2";
    }
}
