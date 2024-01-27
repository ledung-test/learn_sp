package com.example.movie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;
    @Autowired
    private RoleBasedAuthInterceptor roleBasedAuthInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                //Chỉ áp dụng cho các đường dẫn bên dưới
                .addPathPatterns("/api/reviews/**");
        registry.addInterceptor(roleBasedAuthInterceptor)
                .addPathPatterns("/admin", "/admin/**", "/api/admin/**");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image_upload/**")
                .addResourceLocations("file:image_upload/");
    }
}
