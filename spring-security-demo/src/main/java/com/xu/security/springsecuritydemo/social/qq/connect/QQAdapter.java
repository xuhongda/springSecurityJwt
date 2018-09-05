package com.xu.security.springsecuritydemo.social.qq.connect;

import com.xu.security.springsecuritydemo.social.qq.api.QQ;
import com.xu.security.springsecuritydemo.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.social.qq.connect
 * springSecurityJwt
 */
public class QQAdapter implements ApiAdapter<QQ> {
    /**
     * 判断QQ是否连通
     * @param api
     * @return
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();

        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //do noting
    }

}
