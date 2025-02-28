package com.example.demo.interceptors;
import com.example.demo.interceptors.JWTInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptors())
                .addPathPatterns("/student/test")  // 其他接口token验证
                .excludePathPatterns("/student/login");  // 所有用户都放行
    }
}
