package com.xu.springsecurityoauth.config;

import com.xu.springsecurityoauth.propertites.MyOauth2ClientProperties;
import com.xu.springsecurityoauth.propertites.MySecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
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
@Configuration
@EnableAuthorizationServer
public class MyAuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {

    //使用构造器spring注入

    //private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    /**
     * 配置文件
     */
    private final MySecurityProperties mySecurityProperties;

    /**
     * token改存在redis，默认是在内存
     */
    private final TokenStore jwtTokenStore;

    @Autowired
    public MyAuthenticationServerConfig(UserDetailsService userDetailsService, MySecurityProperties mySecurityProperties, TokenStore jwtTokenStore) {
        this.userDetailsService = userDetailsService;
        this.mySecurityProperties = mySecurityProperties;
        this.jwtTokenStore = jwtTokenStore;

    }

    /**
     * jwt需要的两个增强器之一：将uuid转换为jwt
     * 有jwt配置时才生效
     */
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * jwt需要的两个增强器之二：往jwt添加自定义信息
     */
    @Autowired(required = false)
    private TokenEnhancer myTokenEnhancer;

    /**
     * 配置TokenEndpoint 是  /oauth/token处理的入口点
     * <p>
     *  使用JWT ，有两个增强器：
     *  1，使用JwtAccessTokenConverter将uuid的token转为jwt，用秘钥签名
     *  2，由于默认生成uuid token的方法是private，所以通过JwtTokenEnhancer 往jwt里添加一些自定义的信息
     *  在这里拿到增强器的链，把这两个增强器连起来
     * </p>
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        //配置token储存方式
        endpoints.tokenStore(jwtTokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);

        //增强器
        if (jwtAccessTokenConverter != null && myTokenEnhancer != null) {
            //拿到增强器链
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(myTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * 功能：认证服务器会给哪些第三方应用发令牌
     * 覆盖了该方法，application.properties里配置的
     * security.oauth2.client.clientId = imooc
     * security.oauth2.client.clientSecret = imoocsecret
     * 就失效了
     * <p>
     *     1，写死
     *               clients.jdbc(dataSource)就是qq场景用的，有第三方公司注册过来，目前场景是给自己的应用提供接口，所以用内存就行
     *               clients.inMemory()
     *                       //~========================== 在这里配置和写配置文件一样================
     *                       .withClient("imooc") //第三方应用用户名
     *                       .secret("imoocsecret") //密码
     *                       .accessTokenValiditySeconds(7200)//token有效期
     *                       .authorizedGrantTypes("password","refresh_token") //支持的授权模式
     *                       .scopes("all","read","write") //相当于oauth的权限，这里配置了，请求里的必须和这里匹配
     *                       //~=======如果有多个client，这里继续配置
     *                       .and()
     *                       .withClient("xxxxx");
     * </p>
     * <p>
     *       //单个客户端配置
     *                   clients.
     *                        inMemory()  //存在内存中
     *                        .withClient("xuhongda") //client_id  必填
     *                        .secret("7777")
     *                        .accessTokenValiditySeconds(300) //有效秒
     *                        .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code") //该client允许的授权类型,默认为空
     *                        .scopes("webapp","read","write") // 允许的授权范围，如果发送请求不带，用这里配置的
     *                        .resourceIds("yd_web","smartparty","operation-api","property-api","yd_shop")
     *                        .autoApprove(true);
     * </p>
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        //2，读取配置文件
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        //判断是否配置了客户端
        if (ArrayUtils.isNotEmpty(mySecurityProperties.getMyOAuth2Properties().getMyOauth2ClientProperties())) {
            for (MyOauth2ClientProperties config : mySecurityProperties.getMyOAuth2Properties().getMyOauth2ClientProperties()) {
                builder.withClient(config.getClientId())
                        .secret(config.getClientSecret())
                        //.accessTokenValiditySeconds(600) //默认为0 令牌不会过期
                        .authorizedGrantTypes("password", "refresh_token")
                        //.refreshTokenValiditySeconds()
                        .scopes("all", "read", "write");
            }
        }

    }


}
