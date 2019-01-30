package com.xu.springsecurityoauth.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.xu.springsecurityoauth.convert.SubjectAttributeUserTokenConverter;
import com.xu.springsecurityoauth.token.MyTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nimbusds.jose.jwk.RSAKey;

import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.config
 * springSecurityJwt
 */
@Configuration
public class TokenStoreConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public TokenStoreConfig(RedisConnectionFactory redisConnectionFactory, KeyPair keyPair) {
        this.redisConnectionFactory = redisConnectionFactory;
    }


    /**
     * JWT配置
     * ClassName: JwtTokenConfig
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
        public JwtTokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setKeyPair(this.keyPair);
            DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
            accessTokenConverter.setUserTokenConverter(new SubjectAttributeUserTokenConverter());
            converter.setAccessTokenConverter(accessTokenConverter);
            return converter;
        }
    }

    /**
     * Legacy Authorization Server (spring-security-oauth2) does not support any
     * <a href target="_blank" href="https://tools.ietf.org/html/rfc7517#section-5">JWK Set</a> endpoint.
     * <p>
     * This class adds ad-hoc support in order to better support the other samples in the repo.
     */
    @FrameworkEndpoint
    class JwkSetEndpoint {
        KeyPair keyPair;

        public JwkSetEndpoint(KeyPair keyPair) {
            this.keyPair = keyPair;
        }

        @GetMapping("/.well-known/jwks.json")
        @ResponseBody
        public Map<String, Object> getKey(Principal principal) {
            RSAPublicKey publicKey = (RSAPublicKey) this.keyPair.getPublic();
            RSAKey key = new RSAKey.Builder(publicKey).build();
            return new JWKSet(key).toJSONObject();
        }
    }

}
