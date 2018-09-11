package com.xu.springsecurityoauth.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xuhongda on 2018/9/11
 * com.xu.springsecurityoauth.pojo
 * springSecurityJwt
 */
@Data
@ToString
public class LgResponse implements Serializable {

    private static final long serialVersionUID = 5810968702156786321L;
    /**
     * 返回状态码
     * 0：二维码已生成
     * 1：APP已扫码
     * 2：用户确认登录
     * 3：通知 web 端生成新的二维码
     */
    private String code;
    /**
     * 返回lgToken（验证码） 还是否可用 true 表示可用;false 表示失效；
     */
    private Boolean aBoolean;
    /**
     * 返回lgToken
     */
    private String token;

    /**
     * 返回令牌
     */
    private String jwt;

    /**
     * 返回信息
     */
    private String message;
}
