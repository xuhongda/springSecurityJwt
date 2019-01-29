package com.xu.springsecurityoauth.propertites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 *     token client 配置数组
 * </p>
 * @author xuhongda on 2018/9/6
 * com.xu.springsecurityoauth.propertites
 * springSecurityJwt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyOauth2Properties {

    private List<MyOauth2ClientProperties> myOauth2ClientProperties;

}
