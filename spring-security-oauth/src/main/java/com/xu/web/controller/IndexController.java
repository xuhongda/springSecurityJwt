package com.xu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xuhongda on 2019/6/5
 * com.xu.springsecurityoauth.controller
 * springSecurityJwt
 */
@Controller
public class IndexController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }
}
