/**
 * 
 */
package com.xu.security.springsecuritydemo.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.properties
 * springSecurityJwt
 */
@Data
public class QQProperties extends SocialProperties {
	
	private String providerId = "qq";

}
