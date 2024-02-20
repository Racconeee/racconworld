package com.racconworld.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/q/**")
                .addResourceLocations("file:///Users/raccon/study/project/Spring/racconworld_data/img/");
        registry.addResourceHandler("/q1/**")
                .addResourceLocations("file:///Users/raccon/study/project/Spring/racconworld_data/img/");
    }
}
