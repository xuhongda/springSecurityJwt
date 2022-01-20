package com.xu.web.config;

import com.xu.web.propertites.MyOauth2ClientProperties;
import com.xu.web.propertites.MySecurityProperties;
import com.xu.web.token.MyTokenEnhancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import java.util.Arrays;
import java.util.List;


/**
 * <p>
 * 不继承AuthorizationServerConfigurerAdapter，这些bean会自己找，配了，就要自己实现
 * </p>
 *
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.config
 * springSecurityJwt
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class MyAuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;


    private final JwtTokenStore jwtTokenStore;

    private final MySecurityProperties mySecurityProperties;

    private final PasswordEncoder passwordEncoder;

    private final MyTokenEnhancer myTokenEnhancer;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    public MyAuthenticationServerConfig(
            AuthenticationConfiguration authenticationConfiguration, JwtTokenStore jwtTokenStore, MySecurityProperties mySecurityProperties,
            PasswordEncoder passwordEncoder, MyTokenEnhancer myTokenEnhancer, JwtAccessTokenConverter jwtAccessTokenConverter) throws Exception {

        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        this.jwtTokenStore = jwtTokenStore;
        this.mySecurityProperties = mySecurityProperties;
        this.passwordEncoder = passwordEncoder;
        this.myTokenEnhancer = myTokenEnhancer;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        List<MyOauth2ClientProperties> myOauth2ClientProperties = mySecurityProperties.getMyOauth2Properties().getMyOauth2ClientProperties();
        // @formatter:off
        clients.inMemory()
                .withClient(myOauth2ClientProperties.get(0).getClientId())
                .authorizedGrantTypes("password")
                .secret(passwordEncoder.encode(myOauth2ClientProperties.get(0).getClientSecret()))
                .scopes(myOauth2ClientProperties.get(0).getScopes())
                .accessTokenValiditySeconds(myOauth2ClientProperties.get(0).getAccessTokenValiditySeconds())
                .and()
                .withClient("writer")
                .authorizedGrantTypes("password")
                .secret("{noop}secret")
                .scopes("message:write")
                .accessTokenValiditySeconds(600_000_000);
        // @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        //增强链，需要加入 jwtAccessTokenConverter
        enhancerChain.setTokenEnhancers(Arrays.asList(myTokenEnhancer,jwtAccessTokenConverter));
        // @formatter:off
        endpoints
                .authenticationManager(this.authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenStore(jwtTokenStore).
                tokenEnhancer(enhancerChain);
        // @formatter:on
    }


}
