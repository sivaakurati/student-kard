/**
 * 
 */
package com.enuminfo.student.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Kumar
 */
@Configuration
public class RestConfiguration {
	
	public @Bean RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
