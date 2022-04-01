package com.yhproject.operation_together.common.auth.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        String requestURI = request.getRequestURI();
        String token1 = request.getParameter("Authorization");
        String token2 = request.getHeader("Authorization");
        /*if(token==null){
            response.sendRedirect("/login");
            throw new IllegalArgumentException("로그인 먼저 해주세요.");
        }
        if(!jwtTokenProvider.validateToken(token.substring(7))){
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
