package com.example.tinymall.core.config;

import com.example.tinymall.common.interceptor.ResponseResultInterceptor;
import com.example.tinymall.core.annotation.support.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @ClassName WxWebMvcConfiguration
 * @Description
 * @Author jzf
 * @Date 2020-4-14 20:52
 */
@Configuration
public class WxWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private ResponseResultInterceptor responseResultInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }

    /**
     * 开启拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String apiUrl = "/**";
        registry.addInterceptor(responseResultInterceptor).addPathPatterns(apiUrl);
    }
}
