package com.xu.web.service;

import com.xu.web.pojo.LgResponse;

/**
 * <p>
 * web 端操作
 * </p>
 *
 * @author xuhongda on 2018/9/11
 * com.xu.web.service
 * springSecurityJwt
 */
public interface LgService {

    /**
     * 保存lgtoken
     *
     * @param lgToken
     * @return LgResponse
     */
    LgResponse saveLgToken(String lgToken);

    /**
     * redis 轮询
     *
     * @param lgToken
     * @return
     */
    LgResponse query(String lgToken);
}
