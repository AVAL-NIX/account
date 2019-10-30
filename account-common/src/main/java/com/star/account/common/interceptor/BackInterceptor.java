package com.star.account.common.interceptor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 后端拦截器
 *
 * @author zx
 * @date 2019/1/28
 */
@SpringBootConfiguration
public class BackInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestPathInterceptor()).addPathPatterns("/**").excludePathPatterns("/back/**","/static/**");
    }
}
