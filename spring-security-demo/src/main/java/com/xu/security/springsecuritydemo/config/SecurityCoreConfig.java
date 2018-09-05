/**
 * 
 */
package com.xu.security.springsecuritydemo.config;

import com.xu.security.springsecuritydemo.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.core
 * springSecurityJwt
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
