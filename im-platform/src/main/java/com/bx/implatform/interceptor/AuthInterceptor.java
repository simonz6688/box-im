package com.bx.implatform.interceptor;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.bx.implatform.config.JwtProperties;
import com.bx.implatform.enums.ResultCode;
import com.bx.implatform.exception.GlobalException;
import com.bx.implatform.session.UserSession;
import com.bx.imcommon.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //从 http 请求头中取出 token
        String token = request.getHeader("accessToken");
        if (StrUtil.isEmpty(token)) {
            log.error("未登陆，url:{}",request.getRequestURI());
            throw new GlobalException(ResultCode.NO_LOGIN);
        }
        //验证 token
        if(!JwtUtil.checkSign(token, jwtProperties.getAccessTokenSecret())){
            log.error("token已失效，url:{}",request.getRequestURI());
            throw new GlobalException(ResultCode.INVALID_TOKEN);
        }
        // 存放session
        String  strJson = JwtUtil.getInfo(token);
        UserSession userSession = JSON.parseObject(strJson,UserSession.class);
        request.setAttribute("session",userSession);
        return true;
    }
}
