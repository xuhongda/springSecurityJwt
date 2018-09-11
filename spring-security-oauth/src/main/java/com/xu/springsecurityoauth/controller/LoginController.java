package com.xu.springsecurityoauth.controller;

import com.xu.springsecurityoauth.pojo.LgResponse;
import com.xu.springsecurityoauth.service.LgAppService;
import com.xu.springsecurityoauth.service.impl.LgAppServiceImpl;
import com.xu.springsecurityoauth.service.impl.LgServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

/**
 * @author xuhongda on 2018/9/11
 * com.xu.springsecurityoauth.controller
 * springSecurityJwt
 */
@RestController
public class LoginController {

    @Autowired
    private LgAppServiceImpl lgAppService;

    @Autowired
    private LgServiceImpl lgService;

    /**
     * web 端向server 端发起lgtoken 储存
     * 设置四十秒过期时间
     *
     * @return LgResponse
     */
    @GetMapping("/saveLgtoken")
    public LgResponse saveLgtoken(String lgtoken) {
        //储存lgtoken
        LgResponse lgResponse = lgService.saveLgToken(lgtoken);

        return lgResponse;
    }


    /**
     * web 轮询
     *
     * @param lgtoken
     * @return LgResponse
     */
    @GetMapping("/qrcodelogin")
    public LgResponse qrcodelogin(String lgtoken) {

        //查询redis
        LgResponse query = lgService.query(lgtoken);
        return query;
    }


    /**
     * app 扫描二维码
     *
     * @param lgtoken
     * @return
     */
    @GetMapping("/applogin")
    public LgResponse appLogin(String lgtoken) {

        LgResponse lgResponse = lgAppService.appScanner(lgtoken);

        return lgResponse;
    }

    /**
     * app 确认登录
     *
     * @param lgtoken
     * @param jwt
     * @return
     */
    @GetMapping("/appVerify")
    public LgResponse appVerify(String lgtoken, String jwt) {
        LgResponse lgResponse = lgAppService.appVerify(lgtoken, jwt);
        return lgResponse;
    }

    /**
     * app 客户端取消登录；生成新的二维码
     *
     * @return
     */
    @GetMapping("/appCancel")
    public LgResponse appCancel(String lgtoken) {
        LgResponse lgResponse = lgAppService.appCancel(lgtoken);
        return lgResponse;
    }
}
