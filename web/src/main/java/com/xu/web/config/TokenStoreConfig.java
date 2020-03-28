package com.xu.web.config;

import com.xu.web.convert.SubjectAttributeUserTokenConverter;
import com.xu.web.token.MyTokenConvert;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.config
 * springSecurityJwt
 */
@Slf4j
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


        private final KeyPair keyPair;

        @Autowired
        public JwtTokenConfig(KeyPair keyPair) {
            this.keyPair = keyPair;
        }

        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }




        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            log.info("privateKey = {}",keyPair.getPrivate());
            log.info("publicKey = {}",keyPair.getPublic());
            converter.setKeyPair(this.keyPair);
            // converter.setSigningKey("xuhongda");
            DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
            accessTokenConverter.setUserTokenConverter(new SubjectAttributeUserTokenConverter());
            //可以替换使用自己的 AccessTokenConverter
            converter.setAccessTokenConverter(new MyTokenConvert());
            return converter;
        }
    }

}
