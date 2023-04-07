package com.bx.implatform.config;

import com.bx.implatform.enums.ResultCode;
import com.bx.implatform.result.Result;
import com.bx.implatform.result.ResultUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@Slf4j
public class TokenAuthenticFilter extends OncePerRequestFilter {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader("Authorization");
        String uri=request.getRequestURI();
        Result<Void> result;
        if(token == null || redisTemplate.opsForValue().get(token) == null){
            log.warn("token失效或token为空，token为：{}",token);
            if("/login".equals(uri) || "/login/token".equals(uri)) {  //login页不需要token认证
                result = ResultUtils.error(ResultCode.TOKEN_INVALID_OR_EXPIRED);
                filterChain.doFilter(request, response);
            }else {
                result = ResultUtils.error(ResultCode.TOKEN_INVALID_OR_EXPIRED,"redirect");
            }
        }else{
            log.info("token认证成功，token为：{}",token);
            result = ResultUtils.success();
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
