/**
 * 
 */
package com.enuminfo.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Kumar
 */
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class RunTool extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		 return application.sources(RunTool.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RunTool.class);
	}
}
