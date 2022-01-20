### 1. 获取 token 

---
url：http://localhost:8087/oauth/token
post 请求
header:
application/x-www-form-urlencoded
Authorization: Basic eHVob25nZGE6Nzc3N19h 

参数：
username=xu （不能为空）
password=123456
grant_type=password

---
---
### 使用 token
header:
Authorization: bearer +token

---
验证 jwt token：
org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter.doFilter
org.springframework.security.jwt.JwtHelper.decodeAndVerify