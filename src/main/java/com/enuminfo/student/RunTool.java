/**
 * 
 */
package com.enuminfo.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kumar
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class RunTool {

	public static void main(String[] args) {
		SpringApplication.run(RunTool.class);
	}
}
