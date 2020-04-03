package com.xu.springsecurityoauth.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.token
 * springSecurityJwt
 */
@Component
public class MyTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        //往jwt添加的自定义信息
        Map<String, Object> info = new HashMap<>(16);
        info.put("girl", "yan");
        info.put("date","2016-12");
        info.put("home", "JiangXi");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
