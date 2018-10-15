package com.xu.springsecurityoauth.config;

import com.xu.springsecurityoauth.token.MyTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.config
 * springSecurityJwt
 */
@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 配置redis存储token
     * @Description: 配置文件有 com.xu.security.oauth2.storeType = redis 时才生效
     * @param @return
     * @return TokenStore
     * @throws
     * @author lihaoyang
     * @date 2018年3月16日
     */
    @Bean
    @ConditionalOnProperty(prefix = "com.xu.security.oauth2" , name = "storeType" , havingValue = "redis")
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * JWT配置
     * ClassName: JwtTokenConfig
     * @Description:
     * @ConditionalOnProperty 是说，有前缀com.xu.security.oauth2.storeType = jwt 的配置时，这个类里的配置才生效
     *     matchIfMissing =true 意思是当配置文件里不配置com.xu.security.oauth2.storeType = jwt时，配置是生效的
     * @author lihaoyang
     * @date 2018年3月16日
     */
    @Configuration
    @ConditionalOnProperty(prefix = "com.xu.security.oauth2" , name = "storeType" , havingValue = "jwt" , matchIfMissing = true)
    public static class JwtTokenConfig{
        @Bean
        public TokenStore jwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            //指定jwt签名, token 发放安全性的保障
            jwtAccessTokenConverter.setSigningKey("xuhongda");
            return jwtAccessTokenConverter;
        }

        @Bean
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer myTokenEnhancer() {
            return new MyTokenEnhancer();
        }
    }

}
