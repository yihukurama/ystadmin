package com.gdyunst.ystadmin.application.config;

import com.gdyunst.ystadmin.application.component.CORSInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 用registry.addMapping("/*")设置拦截的范围"/*"代表拦截所有请求
 * @author Jieyq
 * @Time 2016-09-06
 */
@Configuration
public class WebApplicationConfig {
	
	@Bean
	public CORSInterceptor corsInterceptor() {
	    return new CORSInterceptor();
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("*")
						.allowCredentials(true);
            }

			@Override
			public void addInterceptors(InterceptorRegistry registry) {
//				  registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");
			}
        };
    }
}