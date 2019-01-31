package com.xu.springsecurityoauth.config;

import com.xu.springsecurityoauth.propertites.MyOauth2ClientProperties;
import com.xu.springsecurityoauth.propertites.MySecurityProperties;
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
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    private final JwtTokenStore jwtTokenStore;

    private final MySecurityProperties mySecurityProperties;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyAuthenticationServerConfig(
            AuthenticationConfiguration authenticationConfiguration,
            JwtAccessTokenConverter jwtAccessTokenConverter, JwtTokenStore jwtTokenStore, MySecurityProperties mySecurityProperties, PasswordEncoder passwordEncoder) throws Exception {

        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.jwtTokenStore = jwtTokenStore;
        this.mySecurityProperties = mySecurityProperties;
        this.passwordEncoder = passwordEncoder;
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
        // @formatter:off
        endpoints
                .authenticationManager(this.authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenStore(jwtTokenStore);
        // @formatter:on
    }

}
