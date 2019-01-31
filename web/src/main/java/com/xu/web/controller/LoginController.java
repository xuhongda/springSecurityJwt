package com.xu.web.controller;

import com.xu.web.pojo.LgResponse;
import com.xu.web.service.impl.LgAppServiceImpl;
import com.xu.web.service.impl.LgServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xuhongda on 2018/9/11
 * com.xu.web.controller
 * springSecurityJwt
 */
@RestController
public class LoginController {

    private final LgAppServiceImpl lgAppService;

    private final LgServiceImpl lgService;

    @Autowired
    public LoginController(LgAppServiceImpl lgAppService, LgServiceImpl lgService) {
        this.lgAppService = lgAppService;
        this.lgService = lgService;
    }

    /**
     * web 端向 server 端发起 lgtoken 储存
     * 设置四十秒过期时间
     *
     * @return LgResponse
     */
    @GetMapping("/saveLgtoken")
    public LgResponse saveLgtoken(@RequestParam String lgtoken) {
        //储存lgtoken
        return lgService.saveLgToken(lgtoken);
    }


    /**
     * web 轮询
     *
     * @param lgtoken
     * @return LgResponse
     */
    @GetMapping("/qrcodelogin")
    public LgResponse qrcodelogin(@RequestParam String lgtoken) {
        //查询redis
        return lgService.query(lgtoken);
    }


    /**
     * app 扫描二维码
     *
     * @param lgtoken
     * @return
     */
    @GetMapping("/applogin")
    public LgResponse appLogin(@RequestParam String lgtoken) {

        return lgAppService.appScanner(lgtoken);

    }

    /**
     * app 确认登录
     *
     * @param lgtoken
     * @param authentication
     * @return
     */
    @GetMapping("/appVerify")
    public LgResponse appVerify(@RequestParam String lgtoken, Authentication authentication) {
        return lgAppService.appVerify(lgtoken, authentication);
    }

    /**
     * app 客户端取消登录；生成新的二维码
     *
     * @return
     */
    @GetMapping("/appCancel")
    public LgResponse appCancel(@RequestParam String lgtoken) {
        return lgAppService.appCancel(lgtoken);
    }
}
