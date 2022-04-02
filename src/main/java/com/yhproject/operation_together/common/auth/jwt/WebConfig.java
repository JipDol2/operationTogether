package com.yhproject.operation_together.common.auth.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
/*        registry.addInterceptor(jwtTokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/api/**","/css/**","/js/**");*/
    }

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor(){
        return new JwtTokenInterceptor();
    }
}
