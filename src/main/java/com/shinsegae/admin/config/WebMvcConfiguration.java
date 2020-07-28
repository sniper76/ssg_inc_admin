package com.shinsegae.admin.config;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:static/";
	
	
	@Bean
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		commonsMultipartResolver.setMaxUploadSizePerFile(10 * 1024 * 1024); //10 * 1024 * 1024 (10MB)
		return commonsMultipartResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/css/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "css/");
		registry.addResourceHandler("/js/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "js/");
		registry.addResourceHandler("/images/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "images/");
		registry.addResourceHandler("/bootstrap/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "bootstrap/");		
		registry.addResourceHandler("/ckeditor5/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "ckeditor5/");
		
		registry.addResourceHandler("/pc/**").addResourceLocations("file:images/");
		registry.addResourceHandler("/mo/**").addResourceLocations("file:images/");
		registry.addResourceHandler("/bnr/**").addResourceLocations("file:images/");
	}
	
}
