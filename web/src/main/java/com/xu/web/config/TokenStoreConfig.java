package com.xu.web.config;

import com.xu.web.token.MyTokenConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.config
 * springSecurityJwt
 */
@Configuration
public class TokenStoreConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public TokenStoreConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * JWT配置
     * ClassName: JwtTokenConfig
     *
     * @author xuhongda
     * @Description: JWT配置
     * @ConditionalOnProperty 是说，有前缀com.xu.security.oauth2.storeType = jwt 的配置时，这个类里的配置才生效
     * matchIfMissing =true 意思是当配置文件里不配置com.xu.security.oauth2.storeType = jwt时，配置是生效的
     * @date 2018年3月16日
     */
    @Configuration
    @ConditionalOnProperty(prefix = "com.xu.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig {
        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            //指定jwt签名, token 发放安全性的保障
            jwtAccessTokenConverter.setSigningKey("xuhongda");
            //自定义 token 解析器
            jwtAccessTokenConverter.setAccessTokenConverter(new MyTokenConvert());
            return jwtAccessTokenConverter;
        }
    }

}
