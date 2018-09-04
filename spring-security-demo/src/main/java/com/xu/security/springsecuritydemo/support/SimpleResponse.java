/**
 * 
 */
package com.xu.security.springsecuritydemo.support;

import lombok.Data;

/**
 * @author xuhongda on 2018/9/4
 * com.xu.security.springsecuritydemo.controller
 * springSecurityJwt
 */
@Data
public class SimpleResponse {
	
	public SimpleResponse(Object content){
		this.content = content;
	}
	
	private Object content;

}
