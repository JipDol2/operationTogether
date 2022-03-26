package com.yhproject.operation_together.common.auth.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        String requestURI = request.getRequestURI();
        String token = request.getHeader("Authorization");
        if(token==null){
            response.sendRedirect("/login?redirect="+requestURI);
            throw new IllegalArgumentException("아이디와 비밀번호 입력이 잘못되었습니다.");
        }
        if(!jwtTokenProvider.validateToken(token)){
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
