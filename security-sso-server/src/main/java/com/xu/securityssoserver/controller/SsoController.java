package com.xu.securityssoserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhongda on 2018/9/10
 * com.xu.securityssoserver.controller
 * springSecurityJwt
 */
@RestController
@RequestMapping("sso")
public class SsoController {

    @GetMapping("hello")
    public String sso() {
        return "hello sso";
    }
}
