package com.shop.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> includePathLists = new ArrayList<>();
        includePathLists.add("/**");
        List<String> excludePathLists = new ArrayList<>();
        excludePathLists.add("/test/goLogin");
        excludePathLists.add("/test/userlogin");
        excludePathLists.add("/static/**");

//        registry.addInterceptor(authenticationInterceptor())
//                .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        registry.addInterceptor(configPathInterceptors())
                .excludePathPatterns(excludePathLists)   //  不拦截
                .addPathPatterns(includePathLists); //  拦截
    }

    @Bean
    public ConfigPathInterceptor configPathInterceptors() {
        return new ConfigPathInterceptor();
    }
}