package com.yhproject.operation_together.common.auth.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/css/**","/js/**","/login","/logout","/api/**");
    }
}
