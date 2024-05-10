//package com.racconworld.global.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Value("${quiz_file.dir}")
//    private String fileDir;
//
//
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/q/**")
//                .addResourceLocations(fileDir);
//        registry.addResourceHandler("/a/**")
//                .addResourceLocations(fileDir);
//    }
//}
