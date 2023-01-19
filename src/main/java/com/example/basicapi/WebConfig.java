package com.example.basicapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${cors-origin.urls}")
	private String CORS_URLS;
	
	@Bean
	public RequestMappingHandlerAdapter reqMappingHandlerAdapter() {
		return new RequestMappingHandlerAdapter();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	}
	
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    	
    }
    
    @Override
	public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
    			.allowedOrigins(CORS_URLS.split(",") )
    			.allowedMethods("GET", "POST","PATCH", "DELETE");
	}
 
}
