package com.xu.springsecurityoauth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhongda on 2019/5/17
 * com.xu.springsecurityoauth.controller
 * springSecurityJwt
 */
@RestController
public class ControllerTest {

    @PostMapping("hello")
    public String login(){
        return "success !";
    }
}
