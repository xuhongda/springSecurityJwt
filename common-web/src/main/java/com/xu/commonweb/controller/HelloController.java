package com.xu.commonweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xuhongda on 2018/9/10
 * com.xu.commonweb.controller
 * springSecurityJwt
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/money")
    public String money() {
        return "redirect:http://localhost:8081/client1/hello";
    }

}
