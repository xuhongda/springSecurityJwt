package com.xu.security.springsecuritydemo.properties;

/**
 * @author xuhongda on 2018/9/5
 * com.xu.security.springsecuritydemo.properties
 * springSecurityJwt
 */
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    private int width = 67;
    private int height = 23;

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

}
