package com.xu.springsecurityoauth.service;

import com.xu.springsecurityoauth.pojo.LgResponse;
import org.springframework.security.core.Authentication;

/**
 * <p>
 * app 客户端操作
 * </p>
 *
 * @author xuhongda on 2018/9/11
 * com.xu.springsecurityoauth.service
 * springSecurityJwt
 */

public interface LgAppService {

    /**
     * APP 扫描
     *
     * @param lgToken 唯一标识符
     * @return LgResponse
     */
    LgResponse appScanner(String lgToken);

    /**
     * APP 确认登录
     *
     * @param loToken
     * @param authentication
     * @return LgResponse
     */
    LgResponse appVerify(String loToken, Authentication authentication);


    /**
     * APP 取消登录
     *
     * @param loToken
     * @return
     */
    LgResponse appCancel(String loToken);

}
