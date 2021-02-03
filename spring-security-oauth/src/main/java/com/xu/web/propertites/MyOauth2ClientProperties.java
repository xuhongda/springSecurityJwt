package com.xu.web.propertites;

import lombok.Data;

/**
 * <P>
 *     token client 参数 配置类
 * </P>
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.propertites
 * springSecurityJwt
 */
@Data
public class MyOauth2ClientProperties {

    private String clientId;

    private String clientSecret;

    private int accessTokenValiditySeconds;

    private String [] authorizedGrantTypes;

    private String [] scopes;


}
