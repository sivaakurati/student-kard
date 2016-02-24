/**
 * 
 */
package com.enuminfo.school.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Kumar
 */
@EnableWebMvc
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {   	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/", "resources:/css/**");
		registry.addResourceHandler("/templates/**").addResourceLocations("/templates/", "classpath:/templates/**");
	}
}
