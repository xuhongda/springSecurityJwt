package com.xu.ssoclient1.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author xuhongda on 2018/9/10
 * com.xu.ssoclient1.controller
 * springSecurityJwt
 */
@Controller
public class helloController {

    private final Log logger = LogFactory.getLog(helloController.class);

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("test")
    @ResponseBody
    public String login() {
        logger.info("test。。。");
        return "test";
    }

    @ResponseBody
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

}
