package com.pushpm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/**").addResourceLocations("/static/","/templates/");
        //静态资源释放
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/", "classpath:/templates/");
    }
}