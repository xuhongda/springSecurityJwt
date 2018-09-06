package com.xu.springsecurityoauth.config;

import com.xu.springsecurityoauth.security.MyUserDetailService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.config
 * springSecurityJwt
 */
@Configuration
public class MyAuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {

    /*
     * 不继承AuthorizationServerConfigurerAdapter，这些bean会自己找，配了，就要自己实现
     */

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    //配置文件
    /*@Autowired
    private SecurityProperties securityProperties;*/

    //token存在redis，默认是在内存
    @Autowired
    private TokenStore tokenStore;

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
    private TokenEnhancer jwtTokenEnhancer;

    /**
     * 配置TokenEndpoint 是  /oauth/token处理的入口点
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(myUserDetailService);

        /**
         * 使用JWT ，有两个增强器：
         *     1，使用JwtAccessTokenConverter将uuid的token转为jwt，用秘钥签名
         *  2，由于默认生成uuid token的方法是private，所以通过ImoocJwtTokenEnhancer 往jwt里添加一些自定义的信息
         *
         *  在这里拿到增强器的链，把这两个增强器连起来
         */
        if(jwtAccessTokenConverter != null && jwtTokenEnhancer != null){
            //拿到增强器链
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtAccessTokenConverter);
            enhancers.add(jwtTokenEnhancer);

            enhancerChain.setTokenEnhancers(enhancers);

            endpoints.tokenEnhancer(enhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * 功能：认证服务器会给哪些第三方应用发令牌
     *        覆盖了该方法，application.properties里配置的
     *                 security.oauth2.client.clientId = imooc
     *                security.oauth2.client.clientSecret = imoocsecret
     *     就失效了
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //1，写死
//        clients.jdbc(dataSource)就是qq场景用的，有第三方公司注册过来，目前场景是给自己的应用提供接口，所以用内存就行
//        clients.inMemory()
//                //~========================== 在这里配置和写配置文件一样================
//                .withClient("imooc") //第三方应用用户名
//                .secret("imoocsecret") //密码
//                .accessTokenValiditySeconds(7200)//token有效期
//                .authorizedGrantTypes("password","refresh_token") //支持的授权模式
//                .scopes("all","read","write") //相当于oauth的权限，这里配置了，请求里的必须和这里匹配
//                //~=======如果有多个client，这里继续配置
//                .and()
//                .withClient("xxxxx");

        //单个客户端配置
        clients.inMemory()
                .withClient("yd_cloud") //client_id  必填
                .secret("kk6qZjmX")
                .scopes("webapp") // 允许的授权范围，如果为空，则不限制范围
                .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")//该client允许的授权类型,默认为空
                .resourceIds("yd_web","smartparty","operation-api","property-api","yd_shop")
                .autoApprove(true)
        ;


        //2，读取配置文件
        /*InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        //判断是否配置了客户端
        String [] strings = new String[]{"xx","yy"};

        if(ArrayUtils.isNotEmpty(strings)){
            for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
                builder.withClient(config.getClientId())
                        .secret(config.getClientSecret())
                        .accessTokenValiditySeconds(600)
                        .authorizedGrantTypes("password","refresh_token") //这些也可以配置也可以写死，看心情
                        .scopes("all","read","write");
            }
        }*/

    }
}
